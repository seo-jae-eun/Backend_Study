package com.example.day13.likes;

import com.example.day13.likes.model.response.CreateLikesRes;
import com.example.day13.member.model.Member;
import com.example.day13.post.PostRepository;
import com.example.day13.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.hibernate.StaleObjectStateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final PostRepository postRepository;


    //    public synchronized CreateLikesRes create(Member member, Long idx) {
    @Transactional
    public CreateLikesRes create(Member member, Long idx) throws StaleObjectStateException {
        Post post = postRepository.findById(idx).get();

        Optional<Likes> result = likesRepository.findByMemberAndPost(member, post);
        Likes likes = null;
        if (result.isPresent()) {
            post.subLikesCount();
            postRepository.save(post);

            likes = result.get();
            likesRepository.deleteById(likes.getIdx());

            return CreateLikesRes.builder().idx(likes.getIdx()).result("삭제 성공").build();
        } else {
            post.addLikesCount();
            postRepository.save(post);
            likes = Likes.builder()
                    .member(member)
                    .post(post)
                    .build();
            likes = likesRepository.save(likes);

            return CreateLikesRes.builder().idx(likes.getIdx()).result("저장 성공").build();
        }
    }
}
