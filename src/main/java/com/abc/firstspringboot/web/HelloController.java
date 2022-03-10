package com.abc.firstspringboot.web;

import com.abc.firstspringboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환

public class HelloController {
    @GetMapping("/hello") // GET 요청을 받을 수 있는 API 생성
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String// 외부에서 api로 넘긴 파라미터를 가져옴
                                            name,
                                     @RequestParam("amount") int
                                            amount) {
        return new HelloResponseDto(name, amount);
    }
}
