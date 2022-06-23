package com.example.hello.Service;

import com.example.hello.Dto.In.User.KakaoUserDto;
import com.example.hello.Dto.Out.User.LoginResultDto;
import com.example.hello.Dto.Out.User.SnsLoginResultDto;
import com.example.hello.Entity.UserEntity;
import com.example.hello.Repository.UserRepository;
import com.example.hello.Token.TokenProvider;
import com.example.hello.Types.UserType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class KakaoLoginService {

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.redirect.url}")
    private String redirectUrl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenProvider tokenProvider;

    @Transactional
    public LoginResultDto kakaoLogin(String code) throws JsonProcessingException {
       // String accessToken = getAccessToken(code);
        KakaoUserDto kakaoUserDto = getKakaoUserInfo(code);
        return hanldeKakaoUserInfo(kakaoUserDto);
    }

    private String getAccessToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        System.out.println(code);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUrl);
        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token?code=" + code,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        log.info("getAccessToken responseBody = {}", responseBody);
        return jsonNode.get("access_token").asText();
    }


    private KakaoUserDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<KakaoUserDto> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                KakaoUserDto.class
        );

        KakaoUserDto kakaoUserDto = response.getBody();

        return kakaoUserDto;
    }

    private LoginResultDto hanldeKakaoUserInfo(KakaoUserDto kakaoUserDto) {

        //코드를 이용해 현재 유저 목록에 있는지 확인
        //있으면 그대로 로그인 결과 내려준다.
        if (userRepository.existsByUserEmail(kakaoUserDto.getKakao_account().getEmail())) {

            UserEntity userEntity = userRepository.findByUserEmail(kakaoUserDto.getKakao_account().getEmail());
            return LoginResultDto.from(userEntity, tokenProvider);
        }
        //없으면 회원가입 처리한다.
        else {

            UserEntity userEntity = UserEntity.snsFrom(kakaoUserDto.getKakao_account().getEmail(), passwordEncoder);
            userRepository.save(userEntity);

            return LoginResultDto.from(userEntity, tokenProvider);
        }
    }
}
