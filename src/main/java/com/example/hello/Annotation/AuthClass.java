package com.example.hello.Annotation;

import com.example.hello.Entity.UserEntity;
import com.example.hello.Exception.ErrorCode;
import com.example.hello.Exception.NoAuthException;
import com.example.hello.Repository.UserRepository;
import com.example.hello.Token.TokenProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

//Auth 어노테이션 구현체
@Component
@Aspect
public class AuthClass {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;

    @Around("@annotation(com.example.hello.Annotation.Auth)")
    public void processCustomAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {

        //해당 메서드의 http 요청을 가져온다,
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Auth annotation = methodSignature.getMethod().getAnnotation(Auth.class);

        UserEntity userEntity = userRepository.findByUserId(tokenProvider.getUserIdFromRequest(request));

        //해당하는 유저가 없으면 에러처리
        if(userEntity == null)
            throw new NoAuthException(ErrorCode.NO_AUTHORIZATION_ERROR);

        //role 권한에 맞지 않으면 에러처리한다. (Admin은 모든 권한을 가진다.)
        String role = tokenProvider.getRoleFromRequest(request);

        boolean isAdmin = role.equals(com.example.hello.Types.UserRole.ADMIN.getUserRoleType());
        boolean isRightAuth = role.equals(annotation.userRole().getUserRoleType());

        if ((!isAdmin) && (!isRightAuth))
            throw new NoAuthException(ErrorCode.NO_AUTHORIZATION_ERROR);

        Object[] args = joinPoint.getArgs();

        Annotation[][] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations();

        for (int argIndex = 0; argIndex < args.length; argIndex++) {
            for (Annotation paramAnnotation : parameterAnnotations[argIndex]) {
                if (paramAnnotation instanceof User) {
                    args[argIndex] = UserDetails.from(userEntity);
                }

            }
        }

        joinPoint.proceed(args);
    }
}
