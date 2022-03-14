package com.abc.firstspringboot.web;

import com.abc.firstspringboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) { // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
        //posts 라는 이름으로 findAllDesc()의 결과를 index에 전달
        model.addAttribute("posts", postsService.findAllDesc());
        return "index"; // 앞뒤는 mustache starter 에서 자동 지정
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
