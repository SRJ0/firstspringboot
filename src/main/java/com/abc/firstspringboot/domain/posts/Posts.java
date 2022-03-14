package com.abc.firstspringboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Setter는 만들지 않는다. 값 변경시 목적과 의도를   나타낼 수 있는 메소드를 추가한다.
//setStatus(false) 보다 cancelOrder() 권장
//기본적으로 생성자를 통해(여기서는 빌더) 값 채우고 db에 삽입, 변경시 해당 public method 호출

@Getter
@NoArgsConstructor  // 기본생성자
@Entity // table 과 매칭될 entity 클래스, thisItem(카멜케이스) -> this_item
public class Posts {

    @Id // primary key
    // pk 생성규칙 spring boot 2.0 에서는 아래 옵션 추가해야  auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 유니크키, 복합키보다 Long타입 Auto 추천
    private Long id;

    //varchar(255)가 기본값
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // @Column 없어도 모든 필드는 컬럼이 된다.
    private String author;

    @Builder // 빌더 패턴 클래스 생성, 생성자의 상단에 선언시 생성자에 포함된 필드만 포함
    // 필드를 명확히 지정가능 builder().a(a).b(b).build();
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
