package org.example.postcommand;

import lombok.RequiredArgsConstructor;
import org.example.postcommand.model.Post;
import org.example.postcommand.model.requset.CreatePostReq;
import org.example.postcommand.model.response.CreatePostRes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostCommandService {
    private final PostRepository postRepository;

    @Transactional
    public CreatePostRes create(CreatePostReq request) {
        Post post = postRepository.save(Post.builder()
                .contents(request.getContents())
                .build()
        );

        return CreatePostRes.builder()
                .idx(post.getIdx())
                .contents(post.getContents())
                .build();
    }
}
