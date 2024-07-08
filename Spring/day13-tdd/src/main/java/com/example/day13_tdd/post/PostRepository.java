package com.example.day13_tdd.post;

import com.example.day13_tdd.post.model.Post;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT COUNT(l) FROM Post p LEFT JOIN p.likes l WHERE p.idx = :postIdx") // JPQL 쿼리
    public Integer getLikesCount(Long postIdx);

    // 비관적 락 : DB 단에서 LOCK
    // PESSIMISTIC_WRITE : 다른 트랜잭션이 읽기 X, 쓰기 X
    // PESSIMISTIC_READ : 다른 트랜잭션이 읽기 O, 쓰기 X
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Post> findById(Long idx);
}