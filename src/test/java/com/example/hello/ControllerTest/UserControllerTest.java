package com.example.hello.ControllerTest;

//import com.example.hello.Dto.Common.UserDto;
import com.example.hello.Dto.In.User.SignUpDto;
import com.example.hello.Entity.UserEntity;
import com.example.hello.Repository.UserRepository;
import com.example.hello.Token.TokenProvider;
import com.example.hello.Types.UserRole;
import com.example.hello.Types.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class UserControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    //유저 정보
    public String userEmail = "user1@exmple.com";
    public String userPw = "example@1234";
    public String userPhone = "010-9152-2550";
    public String userName = "sasa5680";
    public String userAddr1 = "addr1";
    public String userAddr2 = "addr2";
    public String userAddr3 = "addr3";
    public UserRole userRole = UserRole.ADMIN;
    public UserType userType = UserType.EMAIL;

    UserEntity userEntity;

    public void createTestUser(){

        //테스트용 유저를 생성
        UserEntity user = new UserEntity();

        user.setUserEmail(userEmail);
        user.setUserPw(userPw);
        user.setUserPhone(userPhone);
        user.setUser_addr1(userAddr1);
        user.setUser_addr2(userAddr2);
        user.setUser_addr3(userAddr3);
        user.setUserRole(userRole);

        userEntity = userRepository.save(user);
    }

    //유저 생성 테스트
    @Test
    void signUpTest() throws Exception {

        //createTestUser();

        SignUpDto signUpDto = new SignUpDto();

        signUpDto.setUserEmail(userEmail);
        signUpDto.setUserPw(userPw);
        signUpDto.setUserName(userName);
        signUpDto.setUserPhone(userPhone);
        signUpDto.setUser_addr1(userAddr1);
        signUpDto.setUser_addr2(userAddr2);
        signUpDto.setUser_addr3(userAddr3);
        signUpDto.setCode("123456");

        ResultActions resultActions =  this.mockMvc.perform(post("/api/user/create")
                .content(objectMapper.writeValueAsString(signUpDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

}
