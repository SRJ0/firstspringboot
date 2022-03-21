package com.abc.firstspringboot.config.auth;

import com.abc.firstspringboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                //url 별 권한관리 시작
                    .authorizeRequests()
                    //권한관리 대상 지정
                    .antMatchers("/", "/css/**", "images/**","/js/**", "/h2-console/**", "/profile").permitAll() // 전체 허용
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // USER only
                    //기타의 경우 인증된 사용자만
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        //oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정
                        .userInfoEndpoint()
                            // 로그인 성공시 인터페이스 구현체를 등록
                            .userService(customOAuth2UserService);
    }


}
