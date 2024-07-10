package com.example.day13.post.querydsl;

import com.example.day13.post.model.Post;
import com.example.day13.post.model.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private BooleanBuilder builder;
    private QPost post;

    public PostRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.builder = new BooleanBuilder();
        this.post = QPost.post;
    }

    @Override
    public List<Post> findAllWithCriteria(Pageable pageable, String contents, String writer, Integer likesCount) {
//        List<Post> result = queryFactory
//                .selectFrom(post)
//                .where(contentsEq(contents), writerEq(writer), likesCountEq(likesCount))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
        // 이렇게만 해주면 쿼리가 2번 실행됨 (writer를 가져오기 위해 member를 한번 더 select)
        List<Post> result = queryFactory
                .selectFrom(post)
                .where(contentsEq(contents), writerEq(writer), likesCountEq(likesCount))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return result;
    }

    private BooleanExpression contentsEq(String contents) {
        if(contents == null) return null;
        return post.contents.eq(contents);
    }
    private BooleanExpression writerEq(String writer) {
        if(writer == null) return null;
        return post.member.email.eq(writer);
    }
    private BooleanExpression likesCountEq(Integer likesCount) {
        if(likesCount == null) return null;
        return post.likesCount.eq(likesCount);
    }
}
