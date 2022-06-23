package com.example.hello.Controller;

import com.example.hello.Annotation.Auth;
import com.example.hello.Annotation.User;
import com.example.hello.Annotation.UserDetails;
import com.example.hello.Service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@Tag(name = "테스트", description = "test api")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    MailService mailService;

    @Operation(summary = "test hello", description = "hello api example")
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    //auth 테스트용
    @Operation(summary = "test auth", description = "hello api example")
    @Auth(userRole = com.example.hello.Types.UserRole.USER)
    @GetMapping("/auth/{id}")
    public UserDetails auth(String dummy, @User UserDetails userDetails, @PathVariable int id) {
        System.out.println("userRole: " + userDetails.getUserRole());
        System.out.println("userId : " + userDetails.getUserId());
        System.out.println("id : " + id);
        return userDetails;
    }

    @GetMapping("/mail")
    public void sendMail() throws MessagingException {

        mailService.sendTestEmail("asaaa", "sasa5680@naver.com");
    }
}
