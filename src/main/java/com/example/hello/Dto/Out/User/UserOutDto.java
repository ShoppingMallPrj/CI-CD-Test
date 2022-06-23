package com.example.hello.Dto.Out.User;

import com.example.hello.Entity.InquiryEntity;
import com.example.hello.Entity.OrderEntity;
import com.example.hello.Entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Schema(description = "회원의 개인정보가 담긴 dto")
@Data
public class UserOutDto {

    private String userEmail;

    private String userName;

    private String userPhone;

    private String user_addr1;

    private String user_addr2;

    private String user_addr3;

    private LocalDateTime regiDate;

    public static UserOutDto from(UserEntity userEntity, ModelMapper modelMapper){

        return modelMapper.map(userEntity, UserOutDto.class);
    }

}
