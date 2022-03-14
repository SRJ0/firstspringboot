package com.abc.firstspringboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // entity 클래스들이 BaseTimeEntity를 상속시 필드도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) // auditing 포함
public class BaseTimeEntity {

    @CreatedDate // entity 생성, 저장시 시간 자동저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 변경시 자동저장
    private LocalDateTime modifiedDate;
}
