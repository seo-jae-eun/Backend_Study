package com.example.day13.likes;

import com.example.day13.member.model.Member;
import com.example.day13.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository  extends JpaRepository<Likes, Long> {

    Optional<Likes> findByMemberAndPost(Member member, Post post);
}
