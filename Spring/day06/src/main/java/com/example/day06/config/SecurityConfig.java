package com.example.day06.config;

import com.example.day06.jwt.JwtUtil;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;

    // 얘만을 위한 config 파일을 따로 만드는게 더 좋다.
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // 필터 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((auth) -> auth.disable()); // csrf : 웹 해킹 기법 공격 중 하나, 세션 로그인 방식일 때 먹히는 코드여서 끈다.
        http.httpBasic((auth) -> auth.disable()); // ??

        // 기본 페이지
//        http.formLogin(Customizer.withDefaults());

        http.authorizeHttpRequests((auth) ->
                // /test/abc/def << 이런것도 포함(**)
                auth
                        .requestMatchers(HttpMethod.GET,"/test/**", "/admin/**").hasRole("ADMIN") // ADMIN이라는 role이 있는 사람만 접근 가능하게 하는 설정
//                        .requestMatchers("/test/**", "/mypage").authenticated() // 권한이 없으면 403이 뜨게 됨
                        .requestMatchers("/member/**", "/login", "/kakao/**").permitAll() // 접근 허용
                        .anyRequest().authenticated() // 위에서 설정하지 않은 페이지들에 대한 접근 설정
        );
        // 특정 페이지를 차단하고 나머지 다 허용 - 블랙리스트
        // 특정 페이지를 허용하고 나머지 다 차단 - 화이트리스트 (지금 위 설정은 화이트리스트 방식)
        http.addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class); // 뒤에꺼를 내꺼로 바꿔라

        return http.build();
    }
}
