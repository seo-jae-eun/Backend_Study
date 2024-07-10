package com.example.day13.post;

import com.example.day13.member.model.Member;
import com.example.day13.post.model.Post;
import com.example.day13.post.model.requset.CreatePostReq;
import com.example.day13.post.model.response.CreatePostRes;
import com.example.day13.post.model.response.ReadPostRes;
import com.example.day13.post.model.response.ReadPostRes2;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // 게시글 하나를 조회하는데 해당 게시글의 좋아요를 누른 사람의 email도 같이 조회
    @Transactional(readOnly = true)
    public ReadPostRes2 read2(Long idx) {
        Optional<Post> result = postRepository.findByIdx(idx);

        if (result.isPresent()) {
            Post post = result.get();

            return ReadPostRes2.builder()
                    .idx(post.getIdx())
                    .contents(post.getContents())
                    .writer(post.getMember().getEmail())
                    .likesUserEmail(post.getLikes().stream().map((l) -> l.getMember().getEmail()).toList())
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
                    .writer(post.getMember().getEmail())
                    .likesCount(post.getLikesCount())
                    .build());
        }
        return readPostResList;
    }

    public List<ReadPostRes> list2(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idx"));
        // 페이지 번호 필요하면 Page, 필요없이 무한 스크롤이면 Slice
        Page<Post> result = postRepository.findAllWithPaging(pageable);
        List<ReadPostRes> readPostResList = new ArrayList<>();
        for (Post post : result.getContent()) {
            readPostResList.add(ReadPostRes.builder()
                    .idx(post.getIdx())
                    .contents(post.getContents())
                    .writer(post.getMember().getEmail())
                    .likesCount(post.getLikesCount())
                    .build());
        }
        return readPostResList;
    }


    public List<ReadPostRes> search(Integer page, Integer size, String contents, String writer, Integer likesCount) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idx"));
        // 페이지 번호 필요하면 Page, 필요없이 무한 스크롤이면 Slice
        Page<Post> result = postRepository.search(pageable, contents, writer, likesCount);
        List<ReadPostRes> readPostResList = new ArrayList<>();
        for (Post post : result.getContent()) {
            readPostResList.add(ReadPostRes.builder()
                    .idx(post.getIdx())
                    .contents(post.getContents())
                    .writer(post.getMember().getEmail())
                    .likesCount(post.getLikesCount())
                    .build());
        }
        return readPostResList;
    }

//    public List<ReadPostRes2> search3(Integer page, Integer size, String contents, String writer, Integer likesCount) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idx"));
//        // 페이지 번호 필요하면 Page, 필요없이 무한 스크롤이면 Slice
//        Page<Post> result = postRepository.search3(pageable, contents, writer, likesCount);
//        List<ReadPostRes2> readPostResList = new ArrayList<>();
//        for (Post post : result.getContent()) {
//            readPostResList.add(ReadPostRes2.builder()
//                    .idx(post.getIdx())
//                    .contents(post.getContents())
//                    .writer(post.getMember().getEmail())
//                    .likesUserEmail(post.getLikes().stream().map((l) -> l.getMember().getEmail()).toList())
//                    .likesCount(post.getLikesCount())
//                    .build());
//        }
//        return readPostResList;
//    }
}
