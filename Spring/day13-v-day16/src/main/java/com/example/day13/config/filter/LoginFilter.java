package com.example.day13.config.filter;

import com.example.day13.member.model.CustomUserDetails;
import com.example.day13.member.model.request.PostLoginReq;
import com.example.day13.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.*;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;


public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public LoginFilter(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AuthenticationManager authenticationManager1) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager1;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        PostLoginReq dto;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            dto = objectMapper.readValue(messageBody, PostLoginReq.class);
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
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetails user = (CustomUserDetails)authResult.getPrincipal();

        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        GrantedAuthority auth = authorities.iterator().next();
        String role = auth.getAuthority();
        String username = user.getUsername();
        Long idx = user.getMember().getIdx();

        String token = jwtUtil.createToken(idx, username, role);

        response.addHeader("Authorization", "Bearer " + token);
        PrintWriter out = response.getWriter();
        out.println("{\"isSuccess\": true, \"accessToken\": \""+token+"\"}");
    }
}
