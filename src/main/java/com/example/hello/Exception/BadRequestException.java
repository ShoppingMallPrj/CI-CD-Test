package com.example.hello.Exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
