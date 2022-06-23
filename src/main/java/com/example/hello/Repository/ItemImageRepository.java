package com.example.hello.Repository;

import com.example.hello.Entity.InquiryEntity;
import com.example.hello.Entity.ItemImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageRepository extends JpaRepository<ItemImageEntity, Integer> {
}
