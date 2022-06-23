package com.example.hello.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailService {

    private final SpringTemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    private String url = "http://localhost:8080";

    @Value("${spring.mail.password}")
    private String pass;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;

    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(pass);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

    public void setMail(String subject, String body, String email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
        mimeMessageHelper.setFrom(new InternetAddress());
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body, true); //메일을 html 타입으로 보낸다
        sendMail(mimeMessageHelper.getMimeMessage());
    }

    public void sendMail(MimeMessage message) {
        getJavaMailSender().send(message);
    }

    public void sendTestEmail(String subject, String email) throws MessagingException {

        Context context = new Context();
        context.setVariable("logo", url + "/img/img.png");
        context.setVariable("text", "sample");

        String body = templateEngine.process("TestEmail", context);
        setMail(subject, body, email);

    }

    //주문시 주문 내역 이메일 발송
    @Async
    public void sendOrderConfirmMail( ) throws MessagingException {

        log.info("==================================================");
        log.info("주문 알림 메일 발송");
        String email = "메일 주소";
        //if(!EmailValidator.emailValidator(email)) throw new BadRequestException(ErrorCode.NO_EMAIL_PATTERN);
        Context context = new Context();

        String subject = "메일 제목";
        String body = templateEngine.process("OrderEmail", context);
        setMail(subject, body, email);
        log.info("==================================================");
    }

    //유효한 이메일 주소인지 인증하는 메일을 발송
    public void sendConfirmEmail(String code, String email) throws MessagingException {

        Context context = new Context();
        context.setVariable("code", code);

        String subject = "회원가입 인증";
        String body = templateEngine.process("ConfirmEmail", context);
        setMail(subject, body, email);
    }
}
