package com.example.hello.Dto.In.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "회원의 개인정보를 수정할 때 사용하는 dto")
@Data
public class UserUpdateDto {

    private String userPhone;
    private String userAddr1;
    private String userAddr2;
    private String userAddr3;

}
