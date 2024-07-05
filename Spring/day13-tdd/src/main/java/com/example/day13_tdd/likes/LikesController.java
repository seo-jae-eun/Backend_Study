package com.example.day13_tdd.likes;


import com.example.day13_tdd.member.model.CustomUserDetails;
import com.example.day13_tdd.member.model.Member;
import com.example.day13_tdd.post.model.requset.CreatePostReq;
import com.example.day13_tdd.post.model.response.CreatePostRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {
    private final LikesService likesService;


    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public ResponseEntity<String> create(@AuthenticationPrincipal CustomUserDetails customUserDetails, Long idx) {

        Member member = null;
        if (customUserDetails != null) {
            member = customUserDetails.getMember();
        } else {
            return ResponseEntity.badRequest().body("");
        }
        likesService.create(member, idx);
        return ResponseEntity.ok("");
    }

}
