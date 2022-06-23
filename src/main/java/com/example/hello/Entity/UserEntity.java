package com.example.hello.Entity;

import com.example.hello.Dto.In.User.SignUpDto;
import com.example.hello.Types.UserRole;
import com.example.hello.Types.UserType;
import lombok.Data;
import org.hibernate.criterion.Order;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_addr1")
    private String user_addr1;

    @Column(name = "user_addr2")
    private String user_addr2;

    @Column(name = "user_addr3")
    private String user_addr3;

    //유저의 주문내역
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Set<OrderEntity> order = new HashSet<>();

    //유저의 문의사항들
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Set<InquiryEntity> inquiry= new HashSet<>();

    @Column(name = "regi_date")
    @CreatedDate
    private LocalDateTime regiDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;


    public static UserEntity from(SignUpDto signUpDto, PasswordEncoder passwordEncoder){

        UserEntity userEntity = new UserEntity();

        userEntity.setUserEmail(signUpDto.getUserEmail());
        userEntity.setUserPw(passwordEncoder.encode(signUpDto.getUserPw()));
        userEntity.setUserPhone(signUpDto.getUserPhone());
        userEntity.setUser_addr1(signUpDto.getUser_addr1());
        userEntity.setUser_addr2(signUpDto.getUser_addr2());
        userEntity.setUser_addr3(signUpDto.getUser_addr3());
        userEntity.setUserRole(UserRole.USER);

        return userEntity;
    }

    //SNS로 회원가입
    public static UserEntity snsFrom(String userEmail, PasswordEncoder passwordEncoder){

        UserEntity userEntity = new UserEntity();

        byte[] array = new byte[20]; // length is bounded by 7

        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        userEntity.setUserPw(passwordEncoder.encode(generatedString));
        userEntity.setUserEmail(userEmail);
        userEntity.setUserRole(UserRole.USER);

        return userEntity;
    }
}
