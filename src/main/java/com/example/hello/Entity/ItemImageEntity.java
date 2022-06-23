package com.example.hello.Entity;

import lombok.Data;

import javax.persistence.*;

//상품이 가진 이미지들
@Entity
@Table(name = "item_image")
@Data
public class ItemImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "item_id")
    private int itemId;

    //이미지 src 주소
    @Column(name = "image_src")
    private String imageSrc;

    //프로필 사진 여부
    @Column(name = "is_profile")
    private boolean isProfile;

}
