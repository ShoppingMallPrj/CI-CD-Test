package com.example.hello.Entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_review")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ItemReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "item_id")
    private int itemId;

    //작성자 id
    @Column(name = "user_id")
    private int userId;

    @CreatedDate
    @Column(name = "review_date")
    private LocalDateTime reviewDate;

    @Column(name = "review_star")
    private int reviewStar;

    @Column(name = "review_content")
    private String reviewContent;
}
