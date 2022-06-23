package com.example.hello.Entity;


import lombok.Data;

import javax.persistence.*;

//아이템이 가진 옵션들
@Entity
@Table(name = "item_option")
@Data
public class ItemOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private int optionId;

    @Column(name = "item_id")
    private int itemId;

    //옵션의 내용 (ex : size-XL, 색상-검정...)
    @Column(name = "option_content")
    private String optionContent;

    //해당 옵션이 가진 재고 수량
    @Column(name = "option_stock")
    private int optionStock;

    //삭제 여부
    @Column(name = "is_deleted")
    private boolean isDeleted;

}
