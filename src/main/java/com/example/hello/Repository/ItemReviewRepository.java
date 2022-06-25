package com.example.hello.Repository;

import com.example.hello.Entity.ItemReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ItemReviewRepository extends JpaRepository<ItemReviewEntity, Integer> {

    Set<ItemReviewEntity> findByItemId(int itemId);

    void deleteByReviewId(int reviewId);
}
