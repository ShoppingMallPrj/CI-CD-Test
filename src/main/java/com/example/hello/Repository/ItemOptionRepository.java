package com.example.hello.Repository;

import com.example.hello.Entity.InquiryEntity;
import com.example.hello.Entity.ItemOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOptionEntity, Integer> {


}
