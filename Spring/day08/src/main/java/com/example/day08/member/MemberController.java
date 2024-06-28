package com.example.day08.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final EmailVerifyService emailVerifyService;

    @RequestMapping(method = RequestMethod.GET, value = "/verify")
    public ResponseEntity<String> verify(String email, String uuid) {
        if(emailVerifyService.isExist(email, uuid)) {
            memberService.activeMember(email);
            return ResponseEntity.ok("이메일 인증 완료");
        } else {
            return ResponseEntity.ok("이메일 인증이 올바르지 않습니다.");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<String> signup(String username, String password) {

        // UUID를 생성해서 메일로 전송하고 UUID 반환
        String uuid = memberService.sendEmail(username);

        // 회원 정보 일단 저장
        memberService.signup(username, password);

        // UUID를 DB에 저장
        emailVerifyService.save(username, uuid);

        return ResponseEntity.ok("성공");
    }

}
