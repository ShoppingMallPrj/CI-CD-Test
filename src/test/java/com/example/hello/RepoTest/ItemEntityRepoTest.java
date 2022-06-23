package com.example.hello.RepoTest;


import com.example.hello.Dto.Out.Item.ItemListOutDto;
import com.example.hello.Entity.InquiryEntity;
import com.example.hello.Entity.ItemEntity;
import com.example.hello.Entity.UserEntity;
import com.example.hello.Repository.ItemRepository;
import com.example.hello.Repository.UserRepository;
import com.example.hello.Service.ItemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
public class ItemEntityRepoTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    //테스트 값
    String  itemName     = "testItem";
    String  itemDes      = "tesDes";
    float   itemStars    = 2.5f;
    int   itemPrice    = 3000;
    String  itemBrand    = "testBrand";
    String  itemCategory = "testCategory";
    String  itemImage    = "testSrc";
    int     itemStock    = 10;


//    ItemEntity create() {
//
//        ItemUploadDto itemUploadDto = new ItemUploadDto();
//
//        itemUploadDto.setItemName(itemName);
//        itemUploadDto.setItemBrand(itemBrand);
//        itemUploadDto.setItemStock(itemStock);
//        itemUploadDto.setItemCategory(itemCategory);
//        itemUploadDto.setItemPrice(itemPrice);
//        itemUploadDto.setItemDescription(itemDes);
//
//        ItemEntity itemEntity = ItemEntity.from(itemUploadDto);
//
//        return itemEntity;
//    }
//    //테스트용 아이템을 생성
//    @Test
//    @DisplayName("아이템 저장 테스트")
//    public void saveItemTest(){
//
//        ItemEntity itemEntity = create();
//        ItemEntity result = itemRepository.save(itemEntity);
//
//        assertThat(result.getItemName()).isEqualTo(itemName);
//        assertThat(result.getItemBrand()).isEqualTo(itemBrand);
//        assertThat(result.getItemCategory()).isEqualTo(itemCategory);
//        assertThat(result.getItemPrice()).isEqualTo(itemPrice);
//        assertThat(result.getItemDescription()).isEqualTo(itemDes);
//
//        System.out.println(result);
//        itemRepository.delete(result);
//    }
//
//    @AfterEach
//    public void deleteTestItem(){
//
//        itemRepository.deleteByItemName(itemName);
//    }

    @Test
    @DisplayName("findPriceByIdTest")
    public void findPriceByIdTest(){

//        ItemEntity itemEntity = itemRepository.getByItemName(itemName);
//        System.out.println(itemEntity);
//        float price = itemRepository.getItemPriceByItemId(itemEntity.getItemId());
//
//
//        assertThat(price).isEqualTo(itemPrice);
    }
}
