package com.example.day13_tdd.config.filter;


import com.example.day13_tdd.member.model.CustomUserDetails;
import com.example.day13_tdd.member.model.Member;
import com.example.day13_tdd.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;


    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("Bearer 토큰이 없음");
            filterChain.doFilter(request, response);

            return;
        }

        String token = authorization.split(" ")[1];

        if(jwtUtil.isExpired(token)) {
            System.out.println("토큰 만료됨");
            filterChain.doFilter(request, response);
            return;
        }

        Long idx = jwtUtil.getIdx(token);
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        Member member = Member.builder()
                .idx(idx)
                .email(username)
                .role(role)
                .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);


        filterChain.doFilter(request,response);


    }
}
