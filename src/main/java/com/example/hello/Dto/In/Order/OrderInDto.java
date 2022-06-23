package com.example.hello.Dto.In.Order;

import com.example.hello.Annotation.Collection;
import com.example.hello.Entity.OrderEntity;
import com.example.hello.Types.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "주문 정보 입력 시 사용하는 dto")
@Data
public class OrderInDto {

    //주문자 id
    @Schema(description = "주문자 id")
    private int userId;

    //주문 상태(주문 완료, 배송중, 배송완료...)
    @Schema(description = "배송 상태")
    private OrderStatus orderStatus;

    @Schema(description = "배송지 주소")
    @NotBlank(message = "주문 시 주소를 입력해야 합니다!")
    private String orderAddr1;

    @Schema(description = "배송지 주소")
    @NotBlank(message = "주문 시 주소를 입력해야 합니다!")
    private String orderAddr2;

    @Schema(description = "배송지 주소")
    @NotBlank(message = "주문 시 주소를 입력해야 합니다!")
    private String orderAddr3;

    //주문자 주소
    @Schema(description = "배송지 주소")
    @NotBlank(message = "주문 시 주소를 입력해야 합니다!")
    String receiverAddr1;

    @Schema(description = "받는사람 주소")
    @NotBlank(message = "주문 시 주소를 입력해야 합니다!")
    String receiverAddr2;

    @Schema(description = "받는사람 주소")
    @NotBlank(message = "수신자 주소를 입력해야 합니다!")
    String receiverAddr3;

    //받는사람 이름
    @Schema(description = "받는사람 이름")
    @NotBlank(message = "수신자 이름")
    String receiverName;

    //받는사람 전화
    @Schema(description = "받는사람 이름")
    @NotBlank(message = "수신자 번화번호를 입력해야 합니다!")
    String receiverPhone;

    //전체 금액
    private int total;

    @Schema(description = "주문 목록 dto")
    @NotNull
    @Collection(min = 1, max = 10)
    Set<OrderListInDto> orderList;


}