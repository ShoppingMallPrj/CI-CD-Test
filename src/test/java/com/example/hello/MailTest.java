package com.example.hello;

import com.example.hello.Service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
////
@SpringBootTest
public class MailTest {

    @Autowired
    MailService mailService;

    @Test
    public void sendTestMail()  {

        try {
            mailService.sendTestEmail("test Mail", "sasa5680@naver.com");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("mail error");
        }
    }

    @Test
    public void sendConfirmEmail()   {

        String code = "123456";
        String email = "sasa5680@naver.com";

        try {
            mailService.sendConfirmEmail(code, email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
