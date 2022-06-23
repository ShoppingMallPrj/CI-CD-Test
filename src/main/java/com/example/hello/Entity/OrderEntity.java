package com.example.hello.Entity;

import com.example.hello.Types.OrderStatus;
import com.example.hello.Types.UserRole;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order")
@Data
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    //주문자
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    //주문 아이템들
    @OneToMany(targetEntity = OrderListEntity.class)
    @JoinColumn(name = "order_id")
    Set<OrderListEntity> orders = new HashSet<>();

    //주문일
    @Column(name = "order_date")
    @CreatedDate
    private LocalDateTime orderDate;

    //주문 상태(주문 완료, 배송중, 배송완료...)
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //주문자 주소
    @Column(name = "order_addr1")
    String orderAddr1;

    @Column(name = "order_addr2")
    String orderAddr2;

    @Column(name = "order_addr3")
    String orderAddr3;

    //주문자 주소
    @Column(name = "receiver_addr1")
    String receiverAddr1;

    @Column(name = "receiver_addr2")
    String receiverAddr2;

    @Column(name = "receiver_addr3")
    String receiverAddr3;

    //받는사람 이름
    @Column(name = "receiver_name")
    String receiverName;

    //받는사람 전화
    @Column(name = "receiver_phone")
    String receiverPhone;

    //전체 금액
    @Column(name = "total")
    private int total;


}
