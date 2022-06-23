package com.example.hello.Annotation;

import com.example.hello.Entity.UserEntity;
import com.example.hello.Types.UserRole;
import lombok.Data;

@Data
public class UserDetails {

    private int userId;
    private UserRole userRole;

    public static UserDetails from(UserEntity userEntity){

        UserDetails userDetails = new UserDetails();

        userDetails.setUserId(userEntity.getUserId());
        userDetails.setUserRole(userEntity.getUserRole());

        return userDetails;
    }
}
