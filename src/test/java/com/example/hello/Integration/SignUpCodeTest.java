//package com.example.hello.Integration;
//
//import com.example.hello.Service.SignUpCodeService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Transactional
//public class SignUpCodeTest {
//
//    @Autowired
//    SignUpCodeService signUpCodeService;
//
//    @Test
//    @DisplayName("이메일 코드 테스트")
//    public void codeTest(){
//
//        String email = "sasa5680@naver.com";
//
//        String code = signUpCodeService.generateCode("sasa5680@naver.com");
//        boolean resultTrue  = signUpCodeService.isValidCode(email, code);
//        boolean resultFalse = signUpCodeService.isValidCode(email, "vwvsdvsdvs");
//
//        assertThat(resultTrue).isTrue();
//        assertThat(resultFalse).isFalse();
//
//    }
//
//    @Test
//    @DisplayName("유효시간 초과 테스트")
//    public void timeOver() throws InterruptedException {
//
//        String email = "sasa5680@naver.com";
//
//        String code = signUpCodeService.generateCode("sasa5680@naver.com");
//
//        //임시로 유효시간 설정 후 테스트
//        Thread.sleep(2000);
//
//        boolean resultFalse = signUpCodeService.isValidCode(email, code);
//
//        assertThat(resultFalse).isFalse();
//    }
//
//    @Test
//    @DisplayName("업데이트 테스트")
//    public void updateTest() throws InterruptedException {
//
//        String email = "sasa5680@naver.com";
//
//        String code = signUpCodeService.generateCode("sasa5680@naver.com");
//
//        //임시로 유효시간 설정 후 테스트
//        Thread.sleep(2000);
//
//        code = signUpCodeService.generateCode("sasa5680@naver.com");
//
//        boolean resultFalse = signUpCodeService.isValidCode(email, code);
//
//        assertThat(resultFalse).isTrue();
//
//    }
//}
