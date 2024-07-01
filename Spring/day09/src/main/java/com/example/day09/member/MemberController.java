package com.example.day09.member;

import com.example.day09.common.BaseResponse;
import com.example.day09.member.model.request.PostSignupReq;
import com.example.day09.member.model.response.PostSignupRes;
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
    private final EmailVerifyService emailVerifyService;

    @RequestMapping(method = RequestMethod.GET, value = "/verify")
    public ResponseEntity<String> verify(String email, String uuid) {
        if(emailVerifyService.isExist(email, uuid)) {
            memberService.enabledMember(email);
            return ResponseEntity.ok("이메일 인증 완료");
        } else {
            return ResponseEntity.ok("이메일 인증이 올바르지 않습니다.");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<BaseResponse<PostSignupRes>> signup(@RequestBody PostSignupReq dto) {

        // UUID를 생성해서 메일로 전송하고 UUID 반환s
        String uuid = memberService.sendEmail(dto.getEmail());

        // 회원 정보 일단 저장
        BaseResponse<PostSignupRes> response = memberService.signup(dto.getEmail(), dto.getPassword(), dto.getName());

        // UUID를 DB에 저장
        emailVerifyService.save(dto.getEmail(), uuid);

        return ResponseEntity.ok(response);
    }

}
