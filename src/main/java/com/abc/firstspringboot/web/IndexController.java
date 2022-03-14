package com.abc.firstspringboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index"; // 앞뒤는 mustache starter 에서 자동 지정
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
