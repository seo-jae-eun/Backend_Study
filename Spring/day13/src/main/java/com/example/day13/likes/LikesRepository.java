package com.example.day13.likes;

import com.example.day13.likes.model.Likes;
import com.example.day13.member.model.Member;
import com.example.day13.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByMemberIdxAndPostIdx(Long memberIdx, Long postIdx);
}
