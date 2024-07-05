package com.example.day13_tdd.post;

import com.example.day13_tdd.member.model.CustomUserDetails;
import com.example.day13_tdd.member.model.Member;
import com.example.day13_tdd.post.model.requset.CreatePostReq;
import com.example.day13_tdd.post.model.response.CreatePostRes;
import com.example.day13_tdd.post.model.response.ReadPostRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<CreatePostRes> create(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody CreatePostReq request) {
        Member member = null;
        if (customUserDetails != null) {
            member = customUserDetails.getMember();
        }
        CreatePostRes response= postService.create(member, request);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity<ReadPostRes> read(Long idx) {
        ReadPostRes response= postService.read(idx);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<ReadPostRes>> list() {
        List<ReadPostRes> response= postService.list();
        return ResponseEntity.ok(response);
    }
}
