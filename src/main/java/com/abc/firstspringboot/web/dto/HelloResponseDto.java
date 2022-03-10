package com.abc.firstspringboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // 모든 final 필드 생성자를 생성
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
