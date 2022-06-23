package com.example.hello.Exception;

public class RestApiException extends RuntimeException{
    public RestApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
