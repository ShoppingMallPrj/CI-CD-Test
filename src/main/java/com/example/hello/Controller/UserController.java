package com.example.hello.Controller;

import com.example.hello.Annotation.Auth;
import com.example.hello.Annotation.User;
import com.example.hello.Annotation.UserDetails;
import com.example.hello.Dto.In.User.SignUpDto;
import com.example.hello.Dto.Out.User.UserOutDto;
import com.example.hello.Dto.In.User.UserUpdateDto;
import com.example.hello.Service.MailService;
import com.example.hello.Service.SignUpCodeService;
import com.example.hello.Service.UserService;
import com.example.hello.Types.UserRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Tag(name = "user", description = "유저 관련 api")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SignUpCodeService signUpCodeService;

    @Autowired
    MailService mailService;

    //일반 회원가입
    @Operation(summary = "일반 회원가입", description = "이메일로 일반 회원가입을 진행한다.")
    @PostMapping("/create")
    public ResponseEntity<Object> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원가입 시 받아오는 dto",required = true)
            @Valid @RequestBody SignUpDto signUpDto){

        userService.createUser(signUpDto);

        return new ResponseEntity<>(

                HttpStatus.CREATED
        );

    }

    @Operation(summary = "인증 이메일 발송", description = "이메일로 인증용 코드가 담긴 이메일을 발송한다.")
    @GetMapping("/request-email/{email}")
    public void requestAuthEmail(
            @Parameter(description = "요청을 원하는 이메일", in = ParameterIn.PATH)
            @PathVariable String email
    ) throws MessagingException {

        //String code = signUpCodeService.generateCode(email);
        mailService.sendConfirmEmail("123456", email);
    }

    @Operation(summary = "유저 정보 가져오기", description = "id 기준으로 유저 정보를 가져온다.")
    @Auth(userRole = UserRole.USER)
    @GetMapping("/{id}")
    public UserOutDto readUser(
            @Parameter(description = "유저 id")
            @PathVariable int id,
            @User UserDetails userDetails
    ){
        return userService.readUser(userDetails.getUserId());
    }

    @Operation(summary = "유저 정보 수정하기", description = "id 기준으로 유저 정보를 수정한다..")
    @Auth(userRole = UserRole.USER)
    @PostMapping("/{id}")
    public void updateUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "유저정보 수정 시 받아오는 dto", required = true)
            @Valid @RequestBody UserUpdateDto userUpdateDto,
            @PathVariable int id,
            @Parameter(hidden = true)
            @User UserDetails userDetails
    ){
        userService.updateUser(userDetails.getUserId(), userUpdateDto);
    }


    @Operation(summary = "유저 삭제", description = "id 기준으로 유저를 삭제한다.")
    @Auth(userRole = UserRole.USER)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){


    }

}
