package com.example.hello;

import com.example.hello.Entity.*;
import com.example.hello.Repository.*;
import com.example.hello.Service.OrderService;
import com.example.hello.Types.OrderStatus;
import com.example.hello.Types.UserRole;
import com.example.hello.Types.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
public class TestEntity {

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
    public String userEmail = "user1@exmple.com";
    public String userPw = "example@1234";
    public String userPhone = "010-9152-2550";
    public String serName = "sasa5680";
    public String userAddr1 = "addr1";
    public String userAddr2 = "addr2";
    public String userAddr3 = "addr3";
    public UserRole userRole = UserRole.USER;
    public UserType userType = UserType.EMAIL;

    //테스트 아이템
    public String itemName = "testItem";
    public String itemDes = "tesDes";
    public float itemStars = 2.5f;
    public int itemPrice = 3000;
    public String itemBrand = "testBrand";
    public String itemCategory = "testCategory";
    public String itemProfile = "testSrc";

     //테스트 아이템 이미지
    public boolean itemImageProfile = true;
    public String itemImageSrc = "testSrc.com";

     //테스트 아이템 옵션
    public String optionName = "option_content";
    public int optionStock = 10;

     //테스트 주문 정보
    public int orderTotal = 1000;
    public OrderStatus orderStatus = OrderStatus.준비중;

    public UserEntity user;
    public OrderEntity order;
    public OrderListEntity orderList;
    public ItemEntity item;
    public ItemImageEntity itemImage;
    public ItemOptionEntity itemOption;

    public void setTest() {

        //테스트용 유저를 생성
        user = new UserEntity();
        System.out.println(user);
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


}
