package com.example.hello.Dto.Out.Item;

import com.example.hello.Entity.ItemEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Schema(description = "상품 데이터 클라이언트에 전달 시 사용하는 dto")
@Data
public class ItemOutDto {

    private int itemId;

    private String itemName;

    private String itemCategory;

    private String itemDescription;

    private int itemPrice;

    private String gender;

    //연관 아이템들
    private Set<ItemListOutDto> related = new HashSet<>();

    private Set<ItemOptionOutDto> options = new HashSet<>();

    private Set<ItemImageOutDto> images = new HashSet<>();

    private Set<ItemReviewOutDto> reviews = new HashSet<>();

    public static ItemOutDto from(ItemEntity itemEntity, ModelMapper modelMapper){

        ItemOutDto itemOutDto = modelMapper.map(itemEntity, ItemOutDto.class);

        itemOutDto.setOptions(ItemOptionOutDto.from(itemEntity.getOption(), modelMapper));
        itemOutDto.setImages(ItemImageOutDto.from(itemEntity.getImage(), modelMapper));
        itemOutDto.setReviews(ItemReviewOutDto.from(itemEntity.getReview(), modelMapper));

        return itemOutDto;
    }

}
