package com.example.hello.Repository;

import com.example.hello.Entity.ItemEntity;
import com.example.hello.Entity.OrderListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderListEntity, Integer> {
}
