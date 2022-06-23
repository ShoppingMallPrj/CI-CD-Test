package com.example.hello.Integration;

import com.example.hello.Dto.In.Item.ItemOptionInDto;
import com.example.hello.Dto.In.Item.ItemUploadDto;
import com.example.hello.Entity.*;
import com.example.hello.Repository.ItemImageRepository;
import com.example.hello.Repository.ItemOptionRepository;
import com.example.hello.Repository.ItemRepository;
import com.example.hello.Repository.ItemReviewRepository;
import com.example.hello.Service.ItemService;
import com.example.hello.Types.UserRole;
import com.example.hello.Types.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Set;

@Transactional
@SpringBootTest
public class ItemTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemOptionRepository itemOptionRepository;

    @Autowired
    ItemImageRepository itemImageRepository;

    @Autowired
    ItemReviewRepository itemReviewRepository;

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

    //테스트 아이템 리뷰
    String itemReviewContent = "sample review";
    int    itemStar   = 3;


    //유저 정보
    public String userEmail = "user1@exmple.com";
    public String userPw = "example@1234";
    public String userPhone = "010-9152-2550";
    public String userName = "sasa5680";
    public String userAddr1 = "addr1";
    public String userAddr2 = "addr2";
    public String userAddr3 = "addr3";
    public UserRole userRole = UserRole.USER;
    public UserType userType = UserType.EMAIL;

    UserEntity user;
    ItemEntity item;
    ItemImageEntity itemImage;
    ItemOptionEntity itemOption;
    ItemReviewEntity itemReview;

    @Test
    public void createItem(){

        ItemUploadDto itemUploadDto = new ItemUploadDto();


    }

    //아이템 가져오는 테스트
    @Test
    public void fetchItem(){

        System.out.println(itemRepository.findAllByCondition());
    }

    @Test
    public void fetchItemWithSearch(){

        Pageable pageable = PageRequest.of(0, 8);
        System.out.println(itemService.searchItems("TOP", pageable).toList());
    }

    @Test
    public void addItemOption(){

        ItemOptionInDto itemOptionInDto = new ItemOptionInDto();

        itemOptionInDto.setOptionStock(10);
        itemOptionInDto.setOptionContent("added sample");
        itemService.addItemOption(1, itemOptionInDto);

        System.out.println(itemService.itemDetail(1));

    }



    @Test
    public void deleteItemOption(){

        itemService.deleteItemOption(1);
        System.out.println(itemService.itemDetail(1));
    }
}
