package com.example.hello.Service;

import com.example.hello.Dto.In.User.LoginDto;
import com.example.hello.Dto.In.User.SignUpDto;
import com.example.hello.Dto.Out.User.LoginResultDto;
import com.example.hello.Dto.Out.User.UserOutDto;
import com.example.hello.Dto.In.User.UserUpdateDto;
import com.example.hello.Entity.UserEntity;
import com.example.hello.Exception.BadRequestException;
import com.example.hello.Exception.ErrorCode;
import com.example.hello.Exception.NoAuthException;
import com.example.hello.Repository.UserRepository;
import com.example.hello.Token.TokenProvider;
import com.example.hello.Types.UserType;
import com.example.hello.Util.ModelMapperBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

import java.util.Random;

import static com.example.hello.Exception.ErrorCode.ALREADY_EMAIL_ERROR;
import static com.example.hello.Exception.ErrorCode.WRONG_SIGNUP_CODE;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    MailService mailService;

    @Autowired
    ModelMapperBean modelMapperBean;

    @Autowired
    SignUpCodeService signUpCodeService;

    @Autowired
    PasswordEncoder passwordEncoder;

    //일반 계정의 로그인 시 사용
    public LoginResultDto login(LoginDto loginDto) {

        UserEntity userEntity = userRepository.findByUserEmail(loginDto.getEmail());

        if (userEntity == null) {
            throw new NoAuthException(ErrorCode.LOGIN_FAIL_ERROR);
        }

        if (passwordEncoder.matches(passwordEncoder.encode(loginDto.getPassword()), userEntity.getUserPw())){
            throw new NoAuthException(ErrorCode.LOGIN_FAIL_ERROR);
        }

        LoginResultDto loginResultDto = LoginResultDto.from(userEntity, tokenProvider);

        return loginResultDto;
    }

    @Transactional
    public UserEntity createUser(SignUpDto signUpDto) {

        //인증코드가 잘못되면 에러 발생시킨다.
//        if(!signUpCodeService.isValidCode(signUpDto.getUserEmail(), signUpDto.getCode())){
//            throw new NoAuthException(WRONG_SIGNUP_CODE);
//        }

        //이미 존재하는 이메일인지 확인
        boolean exist = userRepository.existsByUserEmail(signUpDto.getUserEmail());

        if (exist) {
            throw new BadRequestException(ALREADY_EMAIL_ERROR);
        } else {

            UserEntity userEntity = UserEntity.from(signUpDto, passwordEncoder);
            userRepository.save(userEntity);
            return userEntity;
        }
    }

    @Transactional
    @Cacheable(value = "user", key = "#userId")
    public UserOutDto readUser(int userId) {

        UserEntity userEntity = userRepository.findByUserId(userId);
        return UserOutDto.from(userEntity, modelMapperBean.modelMapper());

    }

    //유저를 수정한다. 비밀번호, 주소, 휴대전화 번호만 수정이 가능하다.
    @Transactional
    @CachePut(value = "user", key = "#userId")
    public void updateUser(int userId, UserUpdateDto userUpdateDto) {

        UserEntity userEntity = userRepository.findByUserId(userId);

        userEntity.setUserPhone(userUpdateDto.getUserPhone());
        userEntity.setUser_addr1(userUpdateDto.getUserAddr1());
        userEntity.setUser_addr2(userUpdateDto.getUserAddr2());
        userEntity.setUser_addr3(userUpdateDto.getUserAddr3());
    }

    @Transactional
    @CacheEvict(value = "user", key = "#userId")
    public void deleteUser(int userId) {

        userRepository.deleteByUserId(userId);
    }
}
