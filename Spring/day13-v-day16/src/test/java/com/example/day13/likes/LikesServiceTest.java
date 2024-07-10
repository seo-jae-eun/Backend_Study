package com.example.day13.likes;

import com.example.day13.likes.model.response.CreateLikesRes;
import com.example.day13.member.model.Member;
import com.example.day13.post.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class LikesServiceTest {
    @Mock   // 내가 테스트하려는 객체가 의존하고 있는 객체의 가짜 객체
    private LikesRepository likesRepository;
    @InjectMocks    // 내가 테스트하려는 객체의 가짜 객체
    private LikesService likesService;

    @Test
    void likesService_create_when_not_exist_success() {
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


        given(likesRepository.findByMemberAndPost(any(Member.class), any(Post.class))).willReturn(Optional.empty());
        given(likesRepository.save(any(Likes.class))).willReturn(likes);

        // when
        CreateLikesRes response = likesService.create(member, postIdx);


        assertNotNull(response);
        assertEquals(response.getIdx(), likes.getIdx());
        assertEquals(response.getResullt(), "저장 성공");
    }

    @Test
    void likesService_delete_when_exist_success() {
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


        given(likesRepository.findByMemberAndPost(any(Member.class), any(Post.class))).willReturn(Optional.of(likes));

        // when
        CreateLikesRes response = likesService.create(member, postIdx);


        assertNotNull(response);
        assertEquals(response.getIdx(), likes.getIdx());
        assertEquals(response.getResullt(), "삭제 성공");
    }

}