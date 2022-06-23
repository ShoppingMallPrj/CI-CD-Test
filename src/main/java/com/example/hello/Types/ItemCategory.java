package com.example.hello.Types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemCategory {

    coat("COAT");

    private String category;
}
