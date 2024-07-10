package com.example.day13;

import com.example.day13.likes.Likes;
import com.example.day13.likes.LikesRepository;
import com.example.day13.member.MemberRepository;
import com.example.day13.member.model.Member;
import com.example.day13.post.PostRepository;
import com.example.day13.post.model.Post;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void dataInsert() {


        for (int i = 1; i <= 5; i++) {
            Member member = Member.builder()
                    .email("test0" + i + "@test.com")
                    .password(passwordEncoder.encode("qwer1234"))
                    .role("ROLE_USER")
                    .enabled(true)
                    .build();

            memberRepository.save(member);

            for (int j = 1; j <= 5; j++) {
                Post post = Post.builder()
                        .member(member)
                        .contents("상품-" + (j + 5 * (i - 1)) + " by test0" + i + "@test.com")
                        .likesCount(3)
                        .build();
                postRepository.save(post);

            }
        }
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 25; j++) {
                Member member = Member.builder().idx(Long.valueOf(2 * i - 1)).build();
                Post post = Post.builder().idx(Long.valueOf(j))
                        .build();

                Likes likes = Likes.builder()
                        .member(member)
                        .post(post)
                        .build();
                likes = likesRepository.save(likes);

            }
        }
    }
}
