package com.example.hello.Repository;

import com.example.hello.Entity.OrderEntity;
import com.example.hello.Types.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query(value = "select o from OrderEntity o " +
            "where (:orderStatus is NULL or o.orderStatus = :orderStatus)")
    Page<OrderEntity> findAllByCondition(OrderStatus orderStatus, Pageable pageable);

    //유저가 가진 목록 반환
    @Query(value = "select o from OrderEntity o where o.user.userId = :userId")
    Page<OrderEntity> findAllByUser(int userId, Pageable pageable);


    Page<OrderEntity> findAll(Pageable pageable);

    OrderEntity findByOrderId(int orderId);

//    @Query(value = "select if(o.user_id = :userId, true, false) as result from `order` o where order_id = :orderId", nativeQuery = true)
//    boolean isOwnerOfOrder(int userId, int orderId);

    @Query(value = "select case when (o.user.userId = :userId) then true else false end from OrderEntity o where o.orderId = :orderId")
    Boolean isOwnerOfOrder(@Param("userId") Integer userId, @Param("orderId") Integer orderId);

//    @Query(value = "SELECT new java.lang.Boolean((o.user.userId = 1)) from OrderEntity o where o.orderId = 1")
//    Boolean isOwnerOfOrder(@Param("userId") Integer userId, @Param("orderId") Integer orderId);
//
//    @Query(value = "select o.user.userId from OrderEntity o where o.orderId = 1")
//    Integer getUserIdOfOrder(@Param("orderId") Integer orderId);
}
