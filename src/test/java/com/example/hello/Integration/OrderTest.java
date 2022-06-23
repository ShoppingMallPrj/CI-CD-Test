package com.example.hello.Integration;

//import com.example.hello.Dto.Common.OrderDto;
//import com.example.hello.Dto.Common.OrderListDto;
import com.example.hello.Dto.In.Order.OrderInDto;
import com.example.hello.Entity.*;
import com.example.hello.Repository.*;
import com.example.hello.Service.OrderService;
import com.example.hello.TestEntity;
import com.example.hello.Types.OrderStatus;
import com.example.hello.Types.UserRole;
import com.example.hello.Types.UserType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OrderTest {

    //@Autowired
    //TestEntity testEntity;

    @Autowired
    OrderService orderService;

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
    ItemImageRepository itemImageRepository;

    //유저 정보
    String userEmail = "user1@exmple.com";
    String userPw = "example@1234";
    String userPhone = "010-9152-2550";
    String serName = "sasa5680";
    String userAddr1 = "addr1";
    String userAddr2 = "addr2";
    String userAddr3 = "addr3";
    UserRole userRole = UserRole.USER;
    UserType userType = UserType.EMAIL;

    //테스트 아이템
    String itemName = "testItem";
    String itemDes = "tesDes";
    float itemStars = 2.5f;
    int itemPrice = 3000;
    String itemBrand = "testBrand";
    String itemCategory = "testCategory";
    String itemProfile = "testSrc";

    //테스트 아이템 이미지
    boolean itemImageProfile = true;
    String itemImageSrc = "testSrc.com";

    //테스트 아이템 옵션
    String optionName = "option_content";
    int optionStock = 10;

    //테스트 주문 정보
    int orderTotal = 1000;
    OrderStatus orderStatus = OrderStatus.준비중;

    UserEntity user;
    OrderEntity order;
    OrderListEntity orderList;
    ItemEntity item;
    ItemImageEntity itemImage;
    ItemOptionEntity itemOption;


    @BeforeEach
    public void setTest() {

        //테스트용 유저를 생성
        user = new UserEntity();

        user.setUserPhone(userPhone);
        user.setUserRole(userRole);
        user.setUserPw(userPw);
        user.setUserEmail(userEmail);
        user.setUser_addr1(userAddr1);
        user.setUser_addr2(userAddr2);
        user.setUser_addr3(userAddr3);

        userRepository.save(user);

        //테스트용 아이템 생성
        item = new ItemEntity();
        item.setItemPrice(itemPrice);
        item.setItemCategory(itemCategory);
        item.setItemName(itemName);
        item.setItemDescription(itemDes);
        item.setItemProfile(itemProfile);

        itemRepository.save(item);

        //아이템 이미지 추가
        itemImage = new ItemImageEntity();
        itemImage.setItemId(item.getItemId());
        itemImage.setImageSrc(itemImageSrc);
        itemImage.setProfile(true);
        item.getImage().add(itemImage);
        itemImageRepository.save(itemImage);

        //테스트 아이템 옵션
        itemOption = new ItemOptionEntity();

        itemOption.setItemId(item.getItemId());
        itemOption.setOptionStock(optionStock);
        itemOption.setOptionContent(optionName);
        itemOptionRepository.save(itemOption);
        item.getOption().add(itemOption);

        //테스트 주문
        order = new OrderEntity();

        order.setOrderStatus(orderStatus);
        order.setTotal(orderTotal);
        order.setUser(user);
        order.setOrderAddr1(userAddr1);
        order.setOrderAddr2(userAddr2);
        order.setOrderAddr3(userAddr3);

        order = orderRepository.save(order);

        //테스트 주문 목록
        orderList = new OrderListEntity();

        orderList.setItemEntity(item);
        orderList.setItemOptionEntity(itemOption);
        orderList.setOrderId(order.getOrderId());
        orderList.setItemStock(5);

        order.getOrders().add(orderList);
        orderListRepository.save(orderList);

    }

    @Test
    @DisplayName("주문 생성 테스트")
    public void createOrder() {

        OrderInDto orderInDto = new OrderInDto();

    }

    @Test
    public void exist() {

//        OrderInDto orderInDto = new OrderInDto();
//
//        Set<OrderListDto> orderListDtoSet = new HashSet<>();
//        OrderListDto orderListDto = new OrderListDto();
//
//        orderListDto.setOptionId(itemOption.getOptionId());
//        orderListDto.setItemStock(1);
//        orderListDto.setItemId(item.getItemId());
//        orderListDtoSet.add(orderListDto);
//        orderInDto.setUserId(user.getUserId());
//        orderInDto.setOrderList(orderListDtoSet);
//        orderInDto.setOrderAddr1(userAddr1);
//        orderInDto.setOrderAddr2(userAddr2);
//        orderInDto.setOrderAddr3(userAddr3);
//
//
//        System.out.println("-------------------------------------------------------");
//        OrderEntity orderEntity = orderService.createOrder(user.getUserId(), orderInDto);
//        System.out.println(orderEntity);
//        System.out.println(orderEntity.getTotal());

    }



    @Test
    public void EntityToDto() {

//        OrderDto orderDto = OrderDto.from(order);
//
//        System.out.println(orderDto);

    }
}
