package com.example.hello.RepoTest;

import com.example.hello.Entity.UserEntity;
import com.example.hello.Repository.UserRepository;
import com.example.hello.Types.UserRole;
import com.example.hello.Types.UserType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserRepoTest {

    @Autowired
    UserRepository userRepository;

    String userEmail = "user1@exmple.com";
    String userPw = "example@1234";
    String userPhone = "010-9152-2550";
    String userName = "sasa5680";
    String userAddr1 = "addr1";
    String userAddr2 = "addr2";
    String userAddr3 = "addr3";
    UserRole userRole = UserRole.USER;
    UserType userType = UserType.EMAIL;

    //테스트용 유저를 생성
    @BeforeEach
    public void setTestUser(){

        UserEntity userEntity = new UserEntity();

        userEntity.setUserPhone(userPhone);
        userEntity.setUserRole(userRole);
        userEntity.setUserPw(userPw);
        userEntity.setUserEmail(userEmail);
        userEntity.setUser_addr1(userAddr1);
        userEntity.setUser_addr2(userAddr2);
        userEntity.setUser_addr3(userAddr3);

        userRepository.save(userEntity);

    }

    //끝나고 유저 삭제
    @AfterEach
    public void deleteTestUser(){

        //userRepository.deleteByUserEmail(userEmail);
    }

    //이메일 중복여부 테스트
    @Test
    @DisplayName("email duplicate check")
    public void emailDuplicatedTest() {

        boolean resultTrue  = userRepository.existsByUserEmail(userEmail);
        boolean resultFalse = userRepository.existsByUserEmail("asfaqf@aasf.com");

        assertThat(resultTrue).isTrue(); //있는 케이스
        assertThat(resultFalse).isFalse(); //없는 케이스

    }

}
