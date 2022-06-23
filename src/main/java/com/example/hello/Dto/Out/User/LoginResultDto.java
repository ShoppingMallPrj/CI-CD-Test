package com.example.hello.Dto.Out.User;

import com.example.hello.Entity.UserEntity;
import com.example.hello.Token.TokenProvider;
import com.example.hello.Types.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

//로그인 결과들, 유저 정보가 담긴다.
@Schema(description = "로그인 성공 시 결과")
@Data
public class LoginResultDto {

    @Schema(description = "유저 고유 id")
    int userId;

    @Schema(description = "jwt 토큰", nullable = false)
    String token;

    @Schema(description = "jwt 토큰", nullable = false)
    String email;

    @Schema(description = "유저의 role")
    UserRole userRole;

    public static LoginResultDto from(UserEntity userEntity, TokenProvider tokenProvider){

        LoginResultDto loginResultDto = new LoginResultDto();

        loginResultDto.setUserId(userEntity.getUserId());
        loginResultDto.setEmail(userEntity.getUserEmail());
        loginResultDto.setUserRole(userEntity.getUserRole());
        loginResultDto.setToken(tokenProvider.createToken(userEntity.getUserRole(), userEntity.getUserId()));

        return loginResultDto;
    }

}
