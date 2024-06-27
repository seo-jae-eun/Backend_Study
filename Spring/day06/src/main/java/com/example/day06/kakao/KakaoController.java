package com.example.day06.kakao;

import com.example.day06.jwt.JwtUtil;
import com.example.day06.member.MemberService;
import com.example.day06.member.model.CustomUserDetails;
import com.example.day06.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/kakao")
public class KakaoController {
    private final KakaoService kakaoService;
    private final MemberService memberService;
    private final JwtUtil jwtUtil;



    @RequestMapping(method = RequestMethod.GET, value = "/redirect")
    public ResponseEntity<String> kakaoLogin(String code) {
        String accessToken = kakaoService.getKakaoToken(code);


        String nickname = kakaoService.getUserInfo(accessToken);
        // 회원 가입 여부 확인
        Member member = memberService.getMemberByEmail(nickname);

        if(member == null) {
            memberService.signup(nickname, "");
        }

        // JWT 토큰 발급
        String role = "ROLE_USER";
        String username = nickname;

        String token = jwtUtil.createToken(username, role);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);


        return ResponseEntity.ok().headers(headers).body("로그인 성공");
    }
}