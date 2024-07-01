package com.example.day09.oauth;

import com.example.day09.jwt.JwtUtil;
import com.example.day09.member.MemberRepository;
import com.example.day09.member.model.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();

        Map<String, Object> properties = (Map<String, Object>) oAuth2User.getAttributes().get("properties");
        String nickName =  (String) properties.get("nickname");
        String profileImage =  (String) properties.get("profile_image");
        String token;

        // nickname으로 db 확인 후 없으면 회원가입
        Optional<Member> result = memberRepository.findByEmail(nickName);
        if(!result.isPresent()) {
            Member member = Member.builder()
                    .createdAt(LocalDateTime.now())
                    .email(nickName)
                    .password("")
                    .enabled(true)
                    .profileImage(profileImage)
                    .build();
            memberRepository.save(member);
            token = jwtUtil.createToken(nickName, "ROLE_USER", member.getId());
        }
        else {
            token = jwtUtil.createToken(nickName, "ROLE_USER", result.get().getId());
        }

        

        Cookie aToken = new Cookie("aToken", token);
        aToken.setPath("/");
        aToken.setMaxAge(60 * 60 * 24);

        response.addCookie(aToken);

        getRedirectStrategy().sendRedirect(request, response, "http://localhost:3000/member/login/callback");
    }
}
