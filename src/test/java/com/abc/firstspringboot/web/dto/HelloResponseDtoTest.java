package com.abc.firstspringboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombokTest() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // assertj library의 검증메소드 / Junit CoreMatchers와 달리 추가적 라이브러리 불필요, 자동완성 확실히 지원
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount); // 인자로 받은 것과 동등성 비교
    }
}
