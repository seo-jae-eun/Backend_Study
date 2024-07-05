package com.example.day13_tdd.post;

import com.example.day13_tdd.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
