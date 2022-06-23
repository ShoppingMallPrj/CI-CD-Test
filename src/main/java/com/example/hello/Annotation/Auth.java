package com.example.hello.Annotation;

import com.example.hello.Types.UserRole;
import org.hibernate.usertype.UserType;

import java.lang.annotation.*;

// 해당 메서드의 http Header의 jwt 토큰을 검사하여 userRole에 admin 권한을 가지고 있는지 검사
// jwt가 없거나, 잘못된 토큰이거나, admin role이 아니라면 예외발생
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    UserRole userRole() default UserRole.USER;
}
