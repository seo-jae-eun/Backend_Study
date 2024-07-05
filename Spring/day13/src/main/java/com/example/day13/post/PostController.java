package com.example.day13.post;

import com.example.day13.member.MemberService;
import com.example.day13.member.model.CustomUserDetails;
import com.example.day13.member.model.Member;
import com.example.day13.post.model.Post;
import com.example.day13.post.model.request.CreatePostReq;
import com.example.day13.post.model.response.CreatePostRes;
import com.example.day13.post.model.response.ReadPostRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<CreatePostRes> create(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody CreatePostReq createPostReq) {

        Member member = null;
        if (customUserDetails != null) {
            member = customUserDetails.getMember();
        }

        createPostReq.setMemberIdx(memberService.getMemberByEmail(member.getEmail()).getIdx());


        return ResponseEntity.ok(postService.create(createPostReq));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity<ReadPostRes> read(Long idx) {
        return ResponseEntity.ok(postService.read(idx));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<ReadPostRes>> list() {
        return ResponseEntity.ok(postService.list());
    }
}
