package org.example.postquery;

import lombok.RequiredArgsConstructor;
import org.example.postquery.model.Post;
import org.example.postquery.model.response.ReadPostRes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostQueryService {
    private final PostRepository postRepository;

    @Transactional
    public ReadPostRes read(Long idx) {
        Optional<Post> result = postRepository.findById(idx);

        if (result.isPresent()) {
            Post post = result.get();
            return ReadPostRes.builder()
                    .idx(post.getIdx())
                    .contents(post.getContents())
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
