package com.example.day13_tdd.post;

import com.example.day13_tdd.member.model.Member;
import com.example.day13_tdd.post.model.Post;
import com.example.day13_tdd.post.model.requset.CreatePostReq;
import com.example.day13_tdd.post.model.response.CreatePostRes;
import com.example.day13_tdd.post.model.response.ReadPostRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public CreatePostRes create(Member member, CreatePostReq request) {
        Post post = postRepository.save(Post.builder()
                .contents(request.getContents())
                .member(member)
                .build()
        );

        return CreatePostRes.builder()
                .idx(post.getIdx())
                .contents(post.getContents())
                .build();
    }

    @Transactional(readOnly = true)
    public ReadPostRes read(Long idx) {
        Optional<Post> result = postRepository.findById(idx);

        if (result.isPresent()) {
            Post post = result.get();
            return ReadPostRes.builder()
                    .idx(post.getIdx())
                    .contents(post.getContents())
                    .writer(post.getMember().getEmail())
//                    .likesCount(postRepository.getLikesCount(idx))
                    .likesCount(post.getLikesCount())
                    .build();
        }

        return null;
    }

    public List<ReadPostRes> list() {
        List<Post> posts = postRepository.findAll();
        List<ReadPostRes> readPostResList = new ArrayList<>();
        for (Post post : posts) {
            readPostResList.add(ReadPostRes.builder()
                    .idx(post.getIdx())
                    .contents(post.getContents())
                    .build());
        }
        return readPostResList;
    }
}
