package com.abc.firstspringboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// DB layer 접근자, dao와 유사 <entity class, pk type>
// entity class와 함께 위치해야 하며, 프로젝트 분리시 도메인패키지에서 함께 관리
public interface PostsRepository extends JpaRepository<Posts, Long> {
    // spring data jpa의 기본 메소드로도 가능
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
    /*
      조회용 프레임워크 querydsl - 타입 안정성 보장 많이 사용 레퍼런스 많음
     */
}
