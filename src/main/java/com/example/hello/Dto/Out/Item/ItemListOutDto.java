package com.example.hello.Dto.Out.Item;

import com.example.hello.Dto.Out.Inquiry.InquiryOutDto;
import com.example.hello.Entity.InquiryEntity;
import com.example.hello.Entity.ItemEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//목록으로 전달할 경우 아이템의 데이터 (간략화된 데이터)
@Schema(description = "목록으로 전달되는 아이템의 간략화된 데이터")
@Data
public class ItemListOutDto {

    @Schema(description = "아이템 고유 아이디")
    private int itemId;

    @Schema(description = "아이템 이름")
    private String itemName;

    @Schema(description = "아이템 가격")
    private String itemProfile;

    public static ItemListOutDto from(ItemEntity itemEntity, ModelMapper modelMapper) {

        ItemListOutDto itemSimpleDto = modelMapper.map(itemEntity, ItemListOutDto.class);

        return  itemSimpleDto;
    }

    public static Set<ItemListOutDto> from(Set<ItemEntity> itemEntities, ModelMapper modelMapper) {

        Set<ItemListOutDto> itemSimpleDtos = new HashSet<>();

        Iterator<ItemEntity> iterator = itemEntities.iterator();

        while (iterator.hasNext()) {
            itemSimpleDtos.add(ItemListOutDto.from(iterator.next(), modelMapper));
        }
        return  itemSimpleDtos;
    }

    //Entitiy Page에서 Entity dto로 변환
    public static Page<ItemListOutDto> from(Page<ItemEntity> itemEntityPage, ModelMapper modelMapper) {

            Page<ItemListOutDto> itemListDtos = itemEntityPage.map(entity -> {
                ItemListOutDto dto = ItemListOutDto.from(entity, modelMapper);
                return dto;
            });
            return itemListDtos;
        }
}
