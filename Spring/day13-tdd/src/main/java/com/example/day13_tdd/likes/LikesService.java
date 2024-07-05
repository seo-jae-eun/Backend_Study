package com.example.day13_tdd.likes;

import com.example.day13_tdd.member.model.Member;
import com.example.day13_tdd.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    public void create(Member member, Long idx) {

        Optional<Likes> result = likesRepository.findByMemberAndPost(member, Post.builder().idx(idx).build());

        Likes likes = null;
        if(result.isPresent()) {
            likes = result.get();
            likesRepository.deleteById(likes.getIdx());
        } else {
            likes = Likes.builder()
                    .member(member)
                    .post(Post.builder().idx(idx).build())
                    .build();



            likesRepository.save(likes);
        }

    }
}
