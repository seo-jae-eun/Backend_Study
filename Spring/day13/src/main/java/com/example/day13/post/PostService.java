package com.example.day13.post;

import com.example.day13.likes.model.Likes;
import com.example.day13.member.MemberRepository;
import com.example.day13.member.model.Member;
import com.example.day13.post.model.Post;
import com.example.day13.post.model.request.CreatePostReq;
import com.example.day13.post.model.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public CreatePostRes create(CreatePostReq createPostReq) {
        Member member = memberRepository.findById(createPostReq.getMemberIdx()).get();
        Post post = Post.builder()
                .contents(createPostReq.getContents())
                .member(member)
                .build();

        Post savedPost = postRepository.save(post);

        return CreatePostRes.builder()
                .postIdx(savedPost.getIdx())
                .build();
    }

    public ReadPostRes read(Long idx) {
        Post post = postRepository.findById(idx).get();
        ReadMemberRes readMemberRes = ReadMemberRes.builder()
                .idx(post.getMember().getIdx())
                .email(post.getMember().getEmail())
                .build();

        List<ReadLikesRes> likes = new ArrayList<>();
        for(Likes like : post.getLikesList()) {
            ReadMemberRes memberRes = ReadMemberRes.builder()
                    .idx(like.getMember().getIdx())
                    .email(like.getMember().getEmail())
                    .build();
            ReadLikesRes likesRes = ReadLikesRes.builder()
                    .idx(like.getIdx())
                    .member(memberRes)
                    .build();
            likes.add(likesRes);
        }



        return ReadPostRes.builder()
                .idx(post.getIdx())
                .contents(post.getContents())
                .writer(readMemberRes)
                .likesList(likes)
                .build();

    }

    public List<ReadPostRes> list() {
        List<Post> posts = postRepository.findAll();
        List<ReadPostRes> readPostRes = new ArrayList<>();

        List<ReadPostRes> readPostResList = new ArrayList<>();
        for(Post post : posts) {
            ReadMemberRes readMemberRes = ReadMemberRes.builder()
                    .idx(post.getMember().getIdx())
                    .email(post.getMember().getEmail())
                    .build();

            List<ReadLikesRes> likes = new ArrayList<>();
            for(Likes like : post.getLikesList()) {
                ReadMemberRes memberRes = ReadMemberRes.builder()
                        .idx(like.getMember().getIdx())
                        .email(like.getMember().getEmail())
                        .build();
                ReadLikesRes likesRes = ReadLikesRes.builder()
                        .idx(like.getIdx())
                        .member(memberRes)
                        .build();
                likes.add(likesRes);
            }

            readPostResList.add(ReadPostRes.builder()
                    .idx(post.getIdx())
                    .contents(post.getContents())
                    .writer(readMemberRes)
                    .likesList(likes)
                    .build()
            );
        }
        return readPostResList;
    }
}
