package com.example.hello.Integration;

import com.example.hello.Dto.In.Inquiry.InquiryAnswerInDto;
import com.example.hello.Dto.In.Inquiry.InquiryInDto;
import com.example.hello.Entity.InquiryEntity;
import com.example.hello.Entity.UserEntity;
import com.example.hello.Repository.InquiryRepository;
import com.example.hello.Repository.UserRepository;
import com.example.hello.Service.InquiryService;
import com.example.hello.Types.UserRole;
import com.example.hello.Types.UserType;
import com.example.hello.Util.ModelMapperBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class InquiryTest {

    @Autowired
    InquiryService inquiryService;

    @Autowired
    InquiryRepository inquiryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapperBean modelMapper;

    String inquiryTitle = "sample title";
    String inquiryContent = "sample content";
    String inquiryAnswer = "sample answer";

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

    InquiryEntity inquiryEntity;

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


    void createTestInquiry(){

        createTestUser();

        InquiryEntity inquiry = new InquiryEntity();

        inquiry.setInquiryTitle(inquiryTitle);
        inquiry.setInquiryContent(inquiryContent);
        inquiry.setUserEntity(userEntity);
        inquiry.setAnswered(false);

        inquiryEntity = inquiryRepository.save(inquiry);

    }

    @Test
    @DisplayName("문의사항 생성 테스트")
    public void createInquiry(){

        createTestUser();

        InquiryInDto inquiryInDto = new InquiryInDto();

        inquiryInDto.setInquiryTitle(inquiryTitle);
        inquiryInDto.setInquiryContent(inquiryContent);
        inquiryInDto.setUserId(userEntity.getUserId());

        InquiryEntity inquiryEntity =  inquiryService.create(userEntity.getUserId(), inquiryInDto);

        assertThat(inquiryEntity.getUserEntity()).isEqualTo(userEntity);
        assertThat(inquiryEntity.getInquiryTitle()).isEqualTo(inquiryTitle);
        assertThat(inquiryEntity.getInquiryContent()).isEqualTo(inquiryContent);
        assertThat(inquiryEntity.isAnswered()).isEqualTo(false);

    }

    @Test
    @DisplayName("문의사항 답변 테스트")
    public void answerInquiry(){

        createTestInquiry();

        InquiryAnswerInDto inquiryAnswerInDto = new InquiryAnswerInDto();

        String answer = "sample answer";

        inquiryAnswerInDto.setAnswer(answer);

        inquiryService.answerInquiry(inquiryAnswerInDto, inquiryEntity.getInquiryId());

        assertThat(inquiryEntity.getInquiryAnswer()).isEqualTo(answer);

    }

}
