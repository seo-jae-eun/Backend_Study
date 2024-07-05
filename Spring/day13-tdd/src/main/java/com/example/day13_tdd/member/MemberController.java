package com.example.day13_tdd.member;

import com.example.day13_tdd.member.model.request.PostSignupReq;
import com.example.day13_tdd.member.model.response.PostSignupRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<PostSignupRes> signup(@RequestBody PostSignupReq request) throws Exception{
        PostSignupRes response = memberService.signup(request);
        return ResponseEntity.ok(response);

    }

}