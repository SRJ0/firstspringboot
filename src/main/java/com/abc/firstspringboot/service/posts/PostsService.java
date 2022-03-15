package com.abc.firstspringboot.service.posts;

import com.abc.firstspringboot.domain.posts.Posts;
import com.abc.firstspringboot.domain.posts.PostsRepository;
import com.abc.firstspringboot.web.dto.PostsListResponseDto;
import com.abc.firstspringboot.web.dto.PostsResponseDto;
import com.abc.firstspringboot.web.dto.PostsSaveRequestDto;
import com.abc.firstspringboot.web.dto.PostsUpdateReqeustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
// Autowired 대신 생성자로 Bean을 주입받음, 롬복으로 변경시 생성자를 수정해야하는 불편 해소
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateReqeustDto requestDto) {
        // jpa의 영속성 컨텍스트, 엔티티를 영구저장하는 환경을 의미하는 논리적 개념
        // 엔티티 매니저 활성상태에서 트랜잭션 끝나는 시점에 변경을 반영하므로 update 쿼리 불필요 (더티 체킹)
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new
                        IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new
                        IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 조회 속도 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // (posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }

}
