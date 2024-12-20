package com.example.shoppro.config;

import jakarta.servlet.annotation.WebListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@WebListener
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {

        // 아래코드는 board.setTitle().setContent().setWriter() 와 같은 식으로 작성됨
        httpSecurity

                // 권한, 페이지 접속권환
                .authorizeHttpRequests(
                        authorization -> authorization      // /** > 로그인 밑에 error까지도 받아주려함
                                .requestMatchers("/members/login/**").permitAll()      // 로그인페이지는 누구나 접속이 가능한 권한   // 주의! 로그인가능항경우만 접근 가능하게 설정하면 무한루프빠짐
                                .requestMatchers("/board/register").authenticated() // 로그인 한 사람만 접속 가능 (세션 처리했던 내용을 이한줄로 가능)
                                .requestMatchers("/item/register").hasRole("ADMIN") // 사장님만 아이템을 개시할 수 있음
                                .requestMatchers("/admin/**").hasRole("ADMIN")      // /admin/** Url은 ADMIN권한만 접근할 수 있다.
                                .requestMatchers("/user/list").hasRole("ADMIN")     // 유저관리는 관리자만 가능
                                .anyRequest().permitAll()                             // 위에 권한설정 한것들 외에 로그인 안해도 전부 접근 가능  (아래와 중첩불가)
//                            .anyRequest().authenticated()                         // 위에 권한설정 한것들 외에는 다 로그인해서 접근해라 (위와 중첩불가)
                )

                // 위변조 방지 웹에서 form태그 변경 등의 변조를 방지
                // 안할거라서 설정만 할것임
                .csrf( csrf -> csrf.disable() )

                // 로그인
                .formLogin(
                        formLogin -> formLogin.loginPage("/members/login")               // 기본 로그인 페이지 지정
                                .defaultSuccessUrl("/")                               // 로그인이 성공했다면 이 url로 보낸다.
                                .usernameParameter("email")                           // 로그인 <input name="email"> 과 같은가 / 이게 서비스에 유저디테일 메소드에 있는 username부분에 들어감
                        // 컨트롤러로 보낼때~~
                )

                // 로그아웃
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 a태그라 생각
                                // <a href="/user/logout">잘가~~</a>
                                .invalidateHttpSession(true)                      // 세션초기화
                                .logoutSuccessUrl("/")                            // localhost:8090 으로 간다.
                        // DNS주소일 경우 www.naver.com까지로 간다.
                        // 컨트롤러에서 만들어 줄껄??
                )



        // 예외처리 // 로그인이 되지 않은 사용자, 권한이 없는 사용자 접속시 취할 행동들
                .exceptionHandling(
                        a -> a.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                                .accessDeniedHandler(new CustomAccessDeniedHandler())

                )

        // 마지막 종결임, 위에서 작성 편하도록 아래에 빼둠
        ;
        return httpSecurity.build();

    }

    // 페스워드 암호화를 위한 의존성 주입을 위해 객체를 저장하는 컨테이너에 객체등록
    @Bean
    PasswordEncoder passwordEncoder (){

        return new BCryptPasswordEncoder();
    }




}
