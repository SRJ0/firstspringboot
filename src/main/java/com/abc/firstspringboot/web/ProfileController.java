package com.abc.firstspringboot.web;


import lombok.RequiredArgsConstructor;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;
    
    @GetMapping("/profile")
    public String profile() {
        // 실행중인 프로필을 모두 가져온다.
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        //무중단 배포시 real1 2만 사용
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profiles.isEmpty()? "default" : profiles.get(0);
        return  profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
