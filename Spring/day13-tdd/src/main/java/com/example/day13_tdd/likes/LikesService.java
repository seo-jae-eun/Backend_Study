package com.example.day13_tdd.likes;

import com.example.day13_tdd.likes.model.response.CreateLikesRes;
import com.example.day13_tdd.member.model.Member;
import com.example.day13_tdd.post.PostRepository;
import com.example.day13_tdd.post.model.Post;
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

//    public synchronized CreateLikesRes create(Member member, Long idx) { // 가장 간단한 방법 1
    @Transactional
    public CreateLikesRes create(Member member, Long idx) throws StaleObjectStateException {
        Post post = postRepository.findById(idx).get();

        Optional<Likes> result = likesRepository.findByMemberAndPost(member, post);

        Likes likes = null;
        if(result.isPresent()) {
            likes = result.get();
            likesRepository.deleteById(likes.getIdx());

            post.subLikesCount();
            postRepository.save(post);

            return CreateLikesRes.builder().idx(likes.getIdx()).resullt("삭제 성공").build();
        } else {
            likes = Likes.builder()
                    .member(member)
                    .post(post)
                    .build();
            likesRepository.save(likes);

            // 동시에 좋아요를 누르면 이 부분에 동시에 들어오면서 count가 정확하게 세어지지 않음
            post.addLikesCount();
            postRepository.save(post);

            return CreateLikesRes.builder().idx(likes.getIdx()).resullt("저장 성공").build();
        }
    }
}
