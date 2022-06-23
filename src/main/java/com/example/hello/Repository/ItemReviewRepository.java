package com.example.hello.Repository;

import com.example.hello.Entity.ItemReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemReviewRepository extends JpaRepository<ItemReviewEntity, Integer> {
}
