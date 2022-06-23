package com.example.hello.Dto.Out.Item;


import com.example.hello.Entity.ItemImageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

@Schema(description = "응답으로 나가는 상품 이미지의 src")
@Data
public class ItemImageOutDto {

    private int imageId;

    private String imageSrc;

    //프로필 사진 여부
    private boolean isProfile;

    public static ItemImageOutDto from(ItemImageEntity itemImageEntity, ModelMapper modelMapper){

        return modelMapper.map(itemImageEntity, ItemImageOutDto.class);
    }

    public static Set<ItemImageOutDto> from(Set<ItemImageEntity> itemImageEntities, ModelMapper modelMapper){

        Set<ItemImageOutDto> itemImageOutDtos = new HashSet<>();

        for(ItemImageEntity itemImageEntity : itemImageEntities){

            ItemImageOutDto itemImageOutDto = from(itemImageEntity, modelMapper);
            itemImageOutDtos.add(itemImageOutDto);
        }

        return itemImageOutDtos;
    }


}
