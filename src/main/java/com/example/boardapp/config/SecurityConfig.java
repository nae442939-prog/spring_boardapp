package com.example.boardapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 이 클래스를 설정  (configuration) 클래스로 지정
@EnableWebSecurity //시큐리티용 설정파일로 지정
public class SecurityConfig { //시큐리티 설정 클래스 시큐리티: 인증/인가 (예를 들어 로그인) 및 보안 담당 

      //비밀번호를 암호화를 처리하는 객체를 빈 (Bean) 으로 등록하는 과정 
    @Bean //@Bean: 이 메서드는 (passwordEncoder) 가 반환하는 객체를 빈으로 등록한다
    public PasswordEncoder passwordEncoder () { 
        // BCrypt: 암호화 알고리즘
        return new BCryptPasswordEncoder(); // BCryptPassword: PasswordEncoder(인터페이스)의 구현 클래스
    }
   
    //필터 체인 (인증/인가 및 보안 을 처리하는 필터들 )
    @Bean //throws : 이 메서드에서 발생한 예외를 상위 메서드에게 전달한다 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
        // HTTP 요청 (HTTP Request)에 대해 접근 권한을 부여하는 절차를 시작한다 
         .authorizeHttpRequests(auth -> auth  
            //member/join, /member/login 은 누구나 접근 가능하다 
            //permit (허가한다 라는 뜻 )
            .requestMatchers("/member/join","/ member/login").permitAll()
            //나머지 요청은 인증 (로그인)이 필요하다 
            .anyRequest().authenticated())
            //폼 로그인 (아이디/비밀번호 로그인)의 기본 설정 <> OAuth 로그인 , JWT 로그인
         .formLogin(form -> form
            //로그인 페이지의 주소를 지정 한다
            .loginPage("/member/login")
            //로그인 처리 주소
            .loginProcessingUrl("/member/login")
            //로그인에 성공한 경우 이동할 주소 게시물 목록 페이지(/posts)로 이동시킴 
            .defaultSuccessUrl("/posts", true)
            .permitAll())
            //로그아웃 설정
          .logout(logout -> logout
            //로그아웃 요청 주소 (로그아웃 버튼을 누르면 이 주소를 요청 하도록 만든다)
            .logoutUrl("/member/logout")
            //로그아웃에 성공 한 경우 이동 시킬 페이지
            .logoutSuccessUrl("/member/login")
            //로그아웃을 하면 세션(인증 상태)을 무효화 한다
            .invalidateHttpSession(true));

            return http.build();
        }     
}
