package com.example.hello.Dto.Out.Item;

import com.example.hello.Entity.ItemImageEntity;
import com.example.hello.Entity.ItemOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Schema(description = "")
@Data
public class ItemOptionOutDto {

    private int optionId;

    //옵션의 내용 (ex : size-XL, 색상-검정...)
    private String optionContent;

    //해당 옵션이 가진 재고 수량
    private int optionStock;

    public static ItemOptionOutDto from(ItemOptionEntity itemOptionEntity, ModelMapper modelMapper) {

        return modelMapper.map(itemOptionEntity, ItemOptionOutDto.class);
    }

    public static Set<ItemOptionOutDto> from(Set<ItemOptionEntity> itemOptionEntities, ModelMapper modelMapper) {

        Set<ItemOptionOutDto> itemOptionOutDtos = new HashSet<>();

        for (ItemOptionEntity itemOptionEntity : itemOptionEntities) {

            ItemOptionOutDto itemOptionOutDto = from(itemOptionEntity, modelMapper);
            itemOptionOutDtos.add(itemOptionOutDto);
        }
        return itemOptionOutDtos;
    }


}
