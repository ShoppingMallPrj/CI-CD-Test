package com.example.hello.Entity;

import lombok.Data;

import javax.persistence.*;

//주문 시 주문한 상품 1개에 대한 정보
@Entity
@Table(name = "order_list")
@Data
public class OrderListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private int listId;

    @ManyToOne(targetEntity = ItemEntity.class)
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    //해당 상품이 갖는 옵션
    @ManyToOne
    @JoinColumn(name = "option_id")
    private ItemOptionEntity itemOptionEntity;


    @Column(name = "order_id")
    private int orderId;

    //주문 수량
    @Column(name = "item_stock")
    private int itemStock;
}
