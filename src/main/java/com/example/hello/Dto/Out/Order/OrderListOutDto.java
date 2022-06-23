package com.example.hello.Dto.Out.Order;

import com.example.hello.Entity.OrderListEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderListOutDto {

    @Schema(description = "주문 상품의 고유 id", nullable = false)
    @NotNull
    int itemId;

    @Schema(description = "주문 수량", nullable = false)
    @Min(1)
    @Max(99)
    private int itemStock;

    @Schema(description = "상품 옵션의 id", nullable = false)
    private int optionId;

    private String itemProfile;

    private String itemName;

    private String optionContent;

    @Schema(description = "상품 대표 이미지 src")
    private String itemImage;

    public static OrderListOutDto from(OrderListEntity orderListEntity){
        OrderListOutDto orderListOutDto = new OrderListOutDto();

        orderListOutDto.setItemName(orderListEntity.getItemEntity().getItemName());
        orderListOutDto.setItemImage(orderListEntity.getItemEntity().getItemProfile());
        orderListOutDto.setItemStock(orderListEntity.getItemStock());
        orderListOutDto.setOptionContent(orderListEntity.getItemOptionEntity().getOptionContent());
        orderListOutDto.setItemId(orderListEntity.getItemEntity().getItemId());
        orderListOutDto.setOptionId(orderListEntity.getItemOptionEntity().getOptionId());

        return orderListOutDto;
    }

    public static Set<OrderListOutDto> from(Set<OrderListEntity> orderListEntities){

        Set<OrderListOutDto> orderListOutDtos = new HashSet<>();

        for(OrderListEntity orderList : orderListEntities){

            OrderListOutDto orderListOutDto = OrderListOutDto.from(orderList);
            orderListOutDtos.add(orderListOutDto);
        }
        return  orderListOutDtos;
    }
}
