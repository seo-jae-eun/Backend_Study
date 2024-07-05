package com.example.day13_tdd.likes;

import com.example.day13_tdd.member.model.Member;
import com.example.day13_tdd.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository  extends JpaRepository<Likes, Long> {

    Optional<Likes> findByMemberAndPost(Member member, Post post);
}
