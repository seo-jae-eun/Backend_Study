package com.example.day13.post;

import com.example.day13.member.model.CustomUserDetails;
import com.example.day13.member.model.Member;
import com.example.day13.post.model.requset.CreatePostReq;
import com.example.day13.post.model.response.CreatePostRes;
import com.example.day13.post.model.response.ReadPostRes;
import com.example.day13.post.model.response.ReadPostRes2;
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

    @RequestMapping(method = RequestMethod.GET, value = "/read2")
    public ResponseEntity<ReadPostRes2> read2(Long idx) {
        ReadPostRes2 response= postService.read2(idx);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<ReadPostRes>> list() {
        List<ReadPostRes> response= postService.list();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list2")
    public ResponseEntity<List<ReadPostRes>> list2(Integer page, Integer size) {
        List<ReadPostRes> response= postService.list2(page, size);
        return ResponseEntity.ok(response);
    }

    // 게시글 조건 검색 (페이징 O)
    //      게시글 내용, 작성자, 좋아요 수 동적 쿼리 -> 멤버를 한번 더 select 하는데 이게 맞는거??
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<List<ReadPostRes>> search(Integer page, Integer size, String contents, String writer, Integer likesCount) {
        List<ReadPostRes> response= postService.search(page, size, contents, writer, likesCount);
        return ResponseEntity.ok(response);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/search3")
//    public ResponseEntity<List<ReadPostRes2>> search3(Integer page, Integer size, String contents, String writer, Integer likesCount) {
//        List<ReadPostRes2> response= postService.search3(page, size, contents, writer, likesCount);
//        return ResponseEntity.ok(response);
//    }
}
