package com.example.hello.Exception;

public class NoAuthException extends RuntimeException{
    public NoAuthException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
