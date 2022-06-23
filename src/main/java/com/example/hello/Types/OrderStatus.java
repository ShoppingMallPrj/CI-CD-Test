package com.example.hello.Types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    준비중("준비중"),
    배송중("배송중"),
    배송완료("배송완료"),
    배송취소("배송취소"),
    주문취소("주문취소"),
    환불신청("환불신청"),
    환불됨("환불됨");

    private String orderStatus;
}
