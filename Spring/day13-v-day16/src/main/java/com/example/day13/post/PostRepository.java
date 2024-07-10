package com.example.day13.post;

import com.example.day13.post.model.Post;
import com.example.day13.post.querydsl.PostRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    @Query("SELECT COUNT(l) FROM Post p LEFT JOIN p.likes l WHERE p.idx = :postIdx")
    public Integer getLikesCount(Long postIdx);


    @Query("SELECT p FROM Post p JOIN FETCH p.member m")
    // 페이지 번호 필요하면 Page, 필요없이 무한 스크롤이면 Slice
    public Page<Post> findAllWithPaging(Pageable pageable);

    @Query("SELECT p FROM Post p JOIN FETCH p.likes l JOIN FETCH l.member m JOIN FETCH p.member m2 WHERE p.idx = :idx")
    public Optional<Post> findByIdx(Long idx);



    // 비관적 락 : DB 단에서 LOCK
    // PESSIMISTIC_WRITE(다른 트랜잭션이 읽기 X, 쓰기 X)
    // PESSIMISTIC_READ(다른 트랜잭션이 읽기 O, 쓰기 X)
    // 비관적 락 테스트할 때는 낙관적 락 주석 처리
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    public Optional<Post> findById(Long idx);
}
