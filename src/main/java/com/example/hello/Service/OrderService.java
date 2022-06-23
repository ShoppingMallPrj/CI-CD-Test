package com.example.hello.Service;


import com.example.hello.Dto.In.Order.OrderInDto;
import com.example.hello.Dto.In.Order.OrderListInDto;
import com.example.hello.Dto.Out.Order.OrderOutDto;
import com.example.hello.Entity.ItemEntity;
import com.example.hello.Entity.OrderEntity;
import com.example.hello.Entity.OrderListEntity;
import com.example.hello.Repository.*;
import com.example.hello.Types.OrderStatus;
import com.example.hello.Util.ModelMapperBean;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderListRepository orderListRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemOptionRepository itemOptionRepository;

    @Autowired
    MailService mailService;

    @Autowired
    ModelMapper modelMapper;


    //주문 소유자인지 확인
    public boolean isOwner(int userId, int orderId){
        return orderRepository.isOwnerOfOrder(userId, orderId);
    }

    //주문을 목록으로 읽는다. 상태에 따라 분류가 가능하다.
    public Page<OrderOutDto> readList(OrderStatus orderStatus, Pageable pageable){

        Page<OrderEntity> orderEntities = orderRepository.findAllByCondition(orderStatus, pageable);
        Page<OrderOutDto> orderOutDtos = OrderOutDto.from(orderEntities, modelMapper);

        return orderOutDtos;
    }

    //유저의 주문을 목록으로 읽는다.
    public Page<OrderOutDto> readUserOrderList(int userId, Pageable pageable){

        Page<OrderEntity> orderEntities = orderRepository.findAllByUser(userId, pageable);
        Page<OrderOutDto> orderOutDtos = OrderOutDto.from(orderEntities, modelMapper);

        return orderOutDtos;
    }

    //주문 하나를 읽는다.
    public OrderOutDto read(int orderId){

        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderOutDto orderDto = OrderOutDto.from(orderEntity, modelMapper);

        return orderDto;
    }

    //주문을 받는다.
    @Transactional
    public OrderEntity createOrder(int userId, OrderInDto orderInDto) throws MessagingException {

        //Order 엔티티 생성
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setUser(userRepository.getById(userId));
        orderEntity.setOrderAddr1(orderInDto.getOrderAddr1());
        orderEntity.setOrderAddr2(orderInDto.getOrderAddr1());
        orderEntity.setOrderAddr3(orderInDto.getOrderAddr1());
        orderEntity.setReceiverAddr1(orderInDto.getReceiverAddr1());
        orderEntity.setReceiverAddr2(orderInDto.getReceiverAddr1());
        orderEntity.setReceiverAddr3(orderInDto.getReceiverAddr1());
        orderEntity.setReceiverName(orderInDto.getReceiverName());
        orderEntity.setReceiverPhone(orderInDto.getReceiverPhone());

        orderEntity.setOrderStatus(OrderStatus.준비중);

        //임시로 총액 설정
        orderEntity.setTotal(100);

        orderRepository.save(orderEntity);

        int total = 0;

        //orderList 생성
        Set<OrderListEntity> orderListEntities = new HashSet<>();

        for(OrderListInDto orderListInDto  :orderInDto.getOrderList()){
        
            ItemEntity itemEntity = itemRepository.getById(orderListInDto.getItemId());

            OrderListEntity orderList = new OrderListEntity();

            orderList.setOrderId(orderEntity.getOrderId());
            orderList.setItemEntity(itemEntity);
            orderList.setItemStock(orderListInDto.getItemStock());
            orderList.setItemOptionEntity(itemOptionRepository.getById(orderListInDto.getOptionId()));

            orderListEntities.add(orderList);
            System.out.println(itemEntity.getItemPrice());
            System.out.println(orderListInDto.getItemStock());
            total += itemEntity.getItemPrice()*orderListInDto.getItemStock();
        }
        orderEntity.setTotal(total);
        //orderRepository.save(orderEntity);

        orderEntity.setOrders(orderListEntities);
        orderListRepository.saveAll(orderListEntities);

        //주문 완료 이메일 발송
        //mailService.sendOrderConfirmMail();

        return orderEntity;
    }

    //주문을 취소한다.
    @Transactional
    public void cancelOrder(int orderId){

        OrderEntity order = orderRepository.findByOrderId(orderId);
        OrderStatus orderStatus = order.getOrderStatus();

        //준비중일 때 만 취소가 가능하다.
        if(orderStatus == OrderStatus.준비중) {
            order.setOrderStatus(OrderStatus.주문취소);
        }
    }

    //주문 상태를 변경한다. (배송중, 배송완료, 배송취소...)
    @Transactional
    public void changeOrderStatus(OrderStatus orderStatus, int orderId){

        OrderEntity order = orderRepository.findByOrderId(orderId);
        order.setOrderStatus(orderStatus);
    }

}
