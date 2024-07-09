package com.example.memberapi.config.filter;

import com.example.memberapi.model.CustomUserDetails;
import com.example.memberapi.model.request.LoginMemberReq;
import com.example.memberapi.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    // 로그인 요청이 들어오면 실행
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        LoginMemberReq dto;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            dto = objectMapper.readValue(messageBody, LoginMemberReq.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String username = dto.getEmail();
        String password = dto.getPassword();

        // 그림에서 2번
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, password, null);

        // 그림에서 3번
        return authenticationManager.authenticate(authToken);
    }

    // 그림에서 10번
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        CustomUserDetails user = (CustomUserDetails)authResult.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        GrantedAuthority authority = authorities.iterator().next();

        String role = authority.getAuthority();
        String username = user.getUsername();
        String token = jwtUtil.createToken(username, role, user.getMember().getIdx());

        response.addHeader("Authorization", "Bearer " + token);

        PrintWriter out = response.getWriter();
        out.println("{\"isSuccess\": true, \"accessToken\":\""+token+"\"}");
    }
}
