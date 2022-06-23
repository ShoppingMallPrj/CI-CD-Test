package com.example.hello.Dto.In.Item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;


@Schema(description = "상품을 변경할 때 사용하는 dto")
@Data
public class ItemUpdateDto {

    private String itemCategory;

    private String itemBrand;

    private String itemProfile;

    private String itemDescription;

    private int itemPrice;


    //삭제할 이미지들

    //추가할 이미지들

    //변경할 옵션들

    //추가할 옵션들

    //삭제할 옵션들


}
