package com.example.hello.Dto.In.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

//일반 로그인 할 경우 프론트에서 입력받는 dto
@Schema(description = "일반 로그인 할 경우 프론트에서 입력받는 dto")
@Data
public class LoginDto {

    @Schema(description = "유저 이메일", nullable = false)
    @Email(message = "이메일 형식이 아닙니다!")
    String email;

    @Schema(description = "유저 비밀번호", nullable = false)
    @NotNull(message = "비밀번호가 입력되지 않았습니다.")
    String password;

}
