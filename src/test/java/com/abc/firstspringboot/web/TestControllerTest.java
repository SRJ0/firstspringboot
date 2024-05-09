package com.abc.firstspringboot.web;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest // 테스트용 컨텍스트 생성
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
public class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeAll //전체 테스트 시작 전에 1회 실행, static
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }
    @BeforeEach //테스트 케이스를 시작하기 전 매번 실행
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @DisplayName("test name")
    @Test // 테스트 메서드
    public void test() {
//        int a = 1, b= 2;
//        int sum = 3;
//        Assertions.assertEquals(sum, a+b);
//        assertThat(a + b).isEqualTo(sum); //assertj 를 사용해서 가독성을 높일 수 있다. a+b는 sum과 같아야한다.
        //isNotEqualTo(), contains(), doesNotContain(), startsWith(), endsWith(), isEmpty(), isNotEmpty()

        //given 테스트를 위한 사전작업 - 멤버 저장
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "hong"));

        //when 테스트할 기능 - 멤버리스트 조회 API 호출
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        //then 결과 - 200, 0번 요소의 id name 확인
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }

    @AfterEach // 테이스 케이스를 마칠 때마다 실행
    public void cleanUp() {
        memberRepository.deleteAll();
    }
    @AfterAll // 전체 테스트를 마친 뒤 1회 실행, static
    static void afterAll() {
        System.out.println("@AfterAll");
    }

}

