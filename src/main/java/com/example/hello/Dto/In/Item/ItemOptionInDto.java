package com.example.hello.Dto.In.Item;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "아이템 옵션 업로드시 사용하는 dto")
public class ItemOptionInDto {

    //옵션의 내용 (ex : size-XL, 색상-검정...)
    private String optionContent;

    //해당 옵션이 가진 재고 수량
    private int optionStock;
}
