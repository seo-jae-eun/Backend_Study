package com.example.day13.likes;

import com.example.day13.likes.model.request.CreateLikesReq;
import com.example.day13.likes.model.response.CreateLikesRes;
import com.example.day13.member.MemberService;
import com.example.day13.member.model.CustomUserDetails;
import com.example.day13.member.model.Member;
import com.example.day13.member.model.request.CreateMemberReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("likes")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;
    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public ResponseEntity<CreateLikesRes> create(@AuthenticationPrincipal CustomUserDetails customUserDetails, Long idx) {
        Member member = null;
        if (customUserDetails != null) {
            member = customUserDetails.getMember();
        }

        return ResponseEntity.ok(likesService.create(memberService.getMemberByEmail(member.getEmail()).getIdx(), idx));
    }
}
