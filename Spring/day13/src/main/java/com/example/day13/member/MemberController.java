package com.example.day13.member;

import com.example.day13.member.model.request.CreateMemberReq;
import com.example.day13.member.model.response.CreateMemberRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.POST, value = "create")
    public ResponseEntity<CreateMemberRes> create(@RequestBody CreateMemberReq request) {
        return ResponseEntity.ok(memberService.create(request));
    }
}
