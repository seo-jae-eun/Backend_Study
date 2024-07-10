package com.example.day13.post.querydsl;

import com.example.day13.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostRepositoryCustom {
    Page<Post> search(Pageable pageable, String contents, String writer, Integer likesCount);
    Slice<Post> search2(Pageable pageable, String contents, String writer, Integer likesCount);
//    Page<Post> search3(Pageable pageable, String contents, String writer, Integer likesCount);

}
