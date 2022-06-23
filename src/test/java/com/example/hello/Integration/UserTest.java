package com.example.hello.Integration;

import com.example.hello.Dto.In.User.LoginDto;
import com.example.hello.Dto.In.User.SignUpDto;
import com.example.hello.Dto.Out.User.LoginResultDto;
import com.example.hello.Dto.Out.User.UserOutDto;
import com.example.hello.Dto.In.User.UserUpdateDto;
import com.example.hello.Entity.UserEntity;
import com.example.hello.Repository.UserRepository;
import com.example.hello.Service.SignUpCodeService;
import com.example.hello.Service.UserService;
import com.example.hello.Types.UserRole;
import com.example.hello.Types.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;



    //유저 정보
    public String userEmail = "user1@exmple.com";
    public String userPw = "example@1234";
    public String userPhone = "010-9152-2550";
    public String userName = "sasa5680";
    public String userAddr1 = "addr1";
    public String userAddr2 = "addr2";
    public String userAddr3 = "addr3";
    public UserRole userRole = UserRole.USER;
    public UserType userType = UserType.EMAIL;

    UserEntity userEntity;

    public void createTestUser(){

        //테스트용 유저를 생성
        UserEntity user = new UserEntity();

        user.setUserEmail(userEmail);
        user.setUserPw(passwordEncoder.encode(userPw));
        user.setUserPhone(userPhone);
        user.setUser_addr1(userAddr1);
        user.setUser_addr2(userAddr2);
        user.setUser_addr3(userAddr3);
        user.setUserRole(userRole);

        System.out.println(user.getUserPw());

        userEntity = userRepository.save(user);
    }

    @Test
    @DisplayName("유저 인증 테스트")
    public void authTest(){

    }

    @Test
    @DisplayName("유저 로그인 테스트")
    public void loginTest(){

        createTestUser();

        LoginDto loginDto = new LoginDto();

        loginDto.setEmail(userEmail);
        loginDto.setPassword(userPw);

        LoginResultDto loginResultDto = userService.login(loginDto);
        assertThat(loginResultDto.getUserId()).isEqualTo(userEntity.getUserId());
        assertThat(loginResultDto.getUserRole()).isEqualTo(userEntity.getUserRole());

    }


    @Test
    @DisplayName("유저생성 테스트")
    public void createUser(){

        //테스트용 유저를 생성
        SignUpDto signUpDto = new SignUpDto();

        signUpDto.setUserPhone(userPhone);
        signUpDto.setUserEmail(userEmail);
        signUpDto.setUserPw(userPw);
        signUpDto.setUserName(userName);
        signUpDto.setUser_addr1(userAddr1);
        signUpDto.setUser_addr2(userAddr2);
        signUpDto.setUser_addr3(userAddr3);

        UserEntity userEntity = userService.createUser(signUpDto);

        assertThat(userEntity.getUserEmail()).isEqualTo(userEmail);
        assertThat(userEntity.getUserPw()).isEqualTo(userPw);
        assertThat(userEntity.getUserRole()).isEqualTo(userRole);
        assertThat(userEntity.getUser_addr1()).isEqualTo(userAddr1);
        assertThat(userEntity.getUser_addr2()).isEqualTo(userAddr2);
        assertThat(userEntity.getUser_addr3()).isEqualTo(userAddr3);
    }

    @Test
    @DisplayName("유저 읽어오기 테스트")
    public void readUser(){

        createTestUser();

        UserOutDto userOutDto = userService.readUser(userEntity.getUserId());

        assertThat(userOutDto.getUserEmail()).isEqualTo(userEmail);
        assertThat(userOutDto.getUserPhone()).isEqualTo(userPhone);
        assertThat(userOutDto.getUser_addr1()).isEqualTo(userAddr1);
        assertThat(userOutDto.getUser_addr2()).isEqualTo(userAddr2);
        assertThat(userOutDto.getUser_addr3()).isEqualTo(userAddr3);
        assertThat(userOutDto.getRegiDate()).isEqualTo(userEntity.getRegiDate());
    }

    @Test
    @DisplayName("유저 수정 테스트")
    public void updateUser(){

        createTestUser();

        String newAddr1 = "newAddr1";
        String newAddr2 = "newAddr2";
        String newAddr3 = "newAddr3";

        UserUpdateDto userUpdateDto = new UserUpdateDto();

        userUpdateDto.setUserAddr1(newAddr1);
        userUpdateDto.setUserAddr2(newAddr2);
        userUpdateDto.setUserAddr3(newAddr3);

        userService.updateUser(userEntity.getUserId(), userUpdateDto);

        assertThat(userEntity.getUser_addr1()).isEqualTo(newAddr1);
    }

    @Test
    @DisplayName("유저 삭제 테스트")
    public void deleteUserTest(){

        createTestUser();

        userRepository.deleteByUserId(userEntity.getUserId());

        assertThat(userRepository.existsByUserEmail(userEmail)).isFalse();

    }
}
