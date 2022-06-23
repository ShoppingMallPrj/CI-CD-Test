package com.example.hello.Dto.In.User;

import com.example.hello.Types.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "SNS 추가정보 입력 시 정보, 이 정보로 회원가입 진행시킨다.")
@Data
public class SnsSignupDto {

    //소셜 계정의 이메일
    @Email
    @Schema(description = "소셜 계정의 이메일", nullable = false, example = "abc@jiniworld.me")
    private String email;

    //소셜 로그인 종류
    @NotNull
    @Schema(description = "소셜 종류", nullable = false, example = "kakao, google...")
    UserType userType;

    @Pattern(regexp = "^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}$")
    @Schema(description = "사용자 휴대전화 번호", nullable = false, example = "010-1234-5678")
    private String userPhone;

    @NotBlank
    private String Addr1;

    @NotBlank
    private String Addr2;

    @NotBlank
    private String Addr3;


}
