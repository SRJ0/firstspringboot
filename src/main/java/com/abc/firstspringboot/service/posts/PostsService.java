package com.abc.firstspringboot.service.posts;

import com.abc.firstspringboot.domain.posts.PostsRepository;
import com.abc.firstspringboot.web.dto.PostsResponseDto;
import com.abc.firstspringboot.web.dto.PostsSaveRequestDto;
import com.abc.firstspringboot.web.dto.PostsUpdateReqeustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
// Autowired 대신 생성자로 Bean을 주입받음, 롬복으로 변경시 생성자를 수정해야하는 불편 해소
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
/*
    public Long update(Long id, PostsUpdateReqeustDto requestDto) {
    }

    public PostsResponseDto findById(Long id) {
    }
 */
}
