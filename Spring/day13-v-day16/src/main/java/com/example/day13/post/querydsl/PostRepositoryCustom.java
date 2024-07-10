package com.example.day13.post.querydsl;

import com.example.day13.post.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> findAllWithCriteria(Pageable pageable, String contents, String writer, Integer likesCount);
}
