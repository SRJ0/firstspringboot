package com.abc.firstspringboot;

// import : alt(option) + enter
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 여기서부터 설정을 읽어가므로 프로젝트 최상단에 위치해야
@SpringBootApplication

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 내장 was 실행, 똑같은 환경에서 배포가능
    }
}
