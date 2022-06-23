package com.example.hello.Dto.In.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SignUpDto {

    @Schema(description = "유저 이메일", nullable = false, example = "abcdefg@naver.com")
    @Email(message = "이메일 형식이 아닙니다!")
    private String userEmail;

    @Schema(description = "이메일 인증 코드, 6자리 숫자로 구성", nullable = false, example = "12356")
    @NotBlank(message = "확인 코드를 입력해야 합니다!")
    private String code;


    @Schema(description = "유저 비밀번호", nullable = false, example = "example!@1234")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$",
            message = "비밀번호는 문자, 숫자, 특수문자 포함 8자 이상이어야 합니다!")
    private String userPw;

    @Schema(description = "유저 이름", nullable = false, example = "홍길동")
    @NotBlank(message = "이름을 입력하세요!")
    private String userName;

    @Schema(description = "사용자 휴대전화 번호", nullable = false, example = "010-1234-5678")
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "휴대전화 형식이 아닙니다!")
    private String userPhone;

    //@NotBlank(message = "주소를 입력해야 합니다!")
    private String user_addr1;

    //@NotBlank(message = "주소를 입력해야 합니다!")
    private String user_addr2;

    //@NotBlank(message = "주소를 입력해야 합니다!")
    private String user_addr3;
}
