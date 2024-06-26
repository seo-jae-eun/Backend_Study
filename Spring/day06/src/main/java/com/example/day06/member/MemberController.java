package com.example.day06.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    // 매개변수에서 @RequestBody 안달아줬으니까 form-date로 요청해야함
    public ResponseEntity<String> signup(String username, String password) {
        memberService.signup(username, password);
        return ResponseEntity.ok("성공");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/adminsignup")
    public ResponseEntity<String> adminSignup(String username, String password) {
        memberService.adminSignup(username, password);
        return ResponseEntity.ok("성공");
    }
}
