package com.example.day13.likes;

import com.example.day13.likes.model.Likes;
import com.example.day13.likes.model.request.CreateLikesReq;
import com.example.day13.likes.model.response.CreateLikesRes;
import com.example.day13.member.MemberRepository;
import com.example.day13.member.model.Member;
import com.example.day13.post.PostRepository;
import com.example.day13.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public CreateLikesRes create(Long memberIdx, Long postIdx) {

        // 이미 좋아요 누른 게시물이면 좋아요 취소
        if(likesRepository.findByMemberIdxAndPostIdx(memberIdx, postIdx).isPresent()) {
            Likes likes = likesRepository.findByMemberIdxAndPostIdx(memberIdx, postIdx).get();
            likesRepository.delete(likes);

            return CreateLikesRes.builder()
                    .status("삭제")
                    .likesIdx(likes.getIdx())
                    .build();
        } else { // 그렇지 않으면 좋아요 생성
            Member member = memberRepository.findById(memberIdx).get();
            Post post = postRepository.findById(postIdx).get();

            Likes likes = Likes.builder()
                    .member(member)
                    .post(post)
                    .build();

            Likes savedLikes = likesRepository.save(likes);
            return CreateLikesRes.builder()
                    .status("생성")
                    .likesIdx(savedLikes.getIdx())
                    .build();

        }

    }

}
