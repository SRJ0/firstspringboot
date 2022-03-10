package com.abc.firstspringboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// DB layer 접근자, dao와 유사 <entity class, pk type>
// entity class와 함께 위치해야 하며, 프로젝트 분리시 도메인패키지에서 함께 관리
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
