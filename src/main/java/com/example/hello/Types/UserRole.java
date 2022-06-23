package com.example.hello.Types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN");

    private String userRoleType;
}
