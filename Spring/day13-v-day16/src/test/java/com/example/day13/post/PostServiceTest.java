package com.example.day13.post;

import com.example.day13.member.model.Member;
import com.example.day13.post.model.Post;
import com.example.day13.post.model.requset.CreatePostReq;
import com.example.day13.post.model.response.CreatePostRes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock   // 내가 테스트하려는 객체가 의존하고 있는 객체의 가짜 객체
    private PostRepository postRepository;
    @InjectMocks    // 내가 테스트하려는 객체의 가짜 객체
    private PostService postService;

    @Test
    void postService_create_success() {
        // given
        CreatePostReq request = CreatePostReq.builder()
                .contents("내용")
                .build();
        Post post = Post.builder()
                .idx(1L)
                .contents("내용")
                .build();
        Member member = Member.builder()
                .idx(1L)
                .build();
        given(postRepository.save(any(Post.class))).willReturn(post);

        // when
        CreatePostRes response = postService.create(member, request);

        // then
        assertNotNull(response);
        assertEquals(1L, response.getIdx());
        assertEquals("내용", response.getContents());
    }

}