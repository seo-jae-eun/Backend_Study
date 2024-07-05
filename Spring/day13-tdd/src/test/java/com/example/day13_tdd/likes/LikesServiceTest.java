package com.example.day13_tdd.likes;

import com.example.day13_tdd.member.model.CustomUserDetails;
import com.example.day13_tdd.member.model.Member;
import com.example.day13_tdd.post.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LikesServiceTest {
    @Mock   // 내가 테스트하려는 객체가 의존하고 있는 객체의 가짜 객체
    private LikesRepository likesRepository;
    @InjectMocks    // 내가 테스트하려는 객체의 가짜 객체
    private LikesService likesService;

    @Test
    void likesService_create_success() {
        // given
        Long postIdx = 1L;
        Member member = Member.builder()
                .idx(1L)
                .build();
        Likes likes = Likes.builder()
                .idx(1L)
                .member(member)
                .post(Post.builder().idx(postIdx).build())
                .build();
        given(likesRepository.save(any(Likes.class))).willReturn(likes);

        // when
        likesService.create(member, postIdx);
    }
}