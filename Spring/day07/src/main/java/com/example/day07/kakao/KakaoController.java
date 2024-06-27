package com.example.day07.kakao;

import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/kakao")
public class KakaoController {
    private final KakaoService kakaoService;

    public KakaoController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/redirect")
    public ResponseEntity<String> kakaoLogin(String code) {
        System.out.println(code);

        String accessToken = kakaoService.getKakaoToken(code);

        System.out.println(accessToken);

        String nickName = kakaoService.getUserInfo(accessToken);

        // 회원 가입 여부 확인

        // 없으면 회원가입

        // JWT 토큰 발급

        return ResponseEntity.ok(code);
    }

}
