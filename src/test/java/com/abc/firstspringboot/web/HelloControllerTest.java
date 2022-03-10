package com.abc.firstspringboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class) // 테스트 진행시 SpringRunner 실행자를 사용
@WebMvcTest(controllers = HelloController.class) // @Controller @ControllerAdvice 등 사용

public class HelloControllerTest {
    @Autowired // Bean 주입
    private MockMvc mvc; // web api test시 사용

    @Test
    public void returnHello() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 GET 요청
                .andExpect(status().isOk()) // http header 검증
                .andExpect(content().string(hello)); // 내용을 검증
    }
    @Test
    public void returnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                .param("name", name) // 요청 파라미터 설정 String only
                .param("amount", String.valueOf(amount))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // JSON 응답값을 필드별로 검증
                .andExpect(jsonPath("$.amount", is(amount))); // $.필드명
    }
}
