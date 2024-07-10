package com.example.day13.post.querydsl;

import com.example.day13.likes.QLikes;
import com.example.day13.member.model.QMember;
import com.example.day13.post.model.Post;
import com.example.day13.post.model.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private BooleanBuilder builder;
    private QPost post;
    private QMember member;
    private QLikes likes;

    public PostRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.builder = new BooleanBuilder();
        this.post = QPost.post;
        this.member = QMember.member;
        this.likes = QLikes.likes;
    }

    @Override
    public Page<Post> search(Pageable pageable, String contents, String writer, Integer likesCount) {
//        List<Post> result = queryFactory
//                .selectFrom(post)
//                .where(contentsEq(contents), writerEq(writer), likesCountEq(likesCount))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
        // 이렇게만 해주면 쿼리가 2번 실행됨 (writer를 가져오기 위해 member를 한번 더 select)
        List<Post> result = queryFactory
                .selectFrom(post)
                .leftJoin(post.member, member).fetchJoin()
                .where(contentsEq(contents), writerEq(writer), likesCountEq(likesCount))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        // 이렇게 해주면 쿼리가 1번만 실행됨, post 입장에서 member는 ToOne이기 때문에 페치 조인으로 쿼리 최적화


        // 페이지 수까지 조회해주려면 Page를 반환해주어야하는데 Page를 생성하기 위해서는 총 개수가 필요함
        Long total = queryFactory.selectFrom(post).fetchCount();
        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Slice<Post> search2(Pageable pageable, String contents, String writer, Integer likesCount) {
        List<Post> result = queryFactory
                .selectFrom(post)
                .leftJoin(post.member, member).fetchJoin()
                .where(contentsEq(contents), writerEq(writer), likesCountEq(likesCount))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();

        Boolean hasnext = false;

        // Slice는 총 개수를 알 필요는 없지만 다음 페이지가 있는지는 알아야 됨
        if(result.size() == pageable.getPageSize()+1) {
            hasnext = true;
        }

        return new SliceImpl<>(result, pageable, hasnext);
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

//    @Override
//    public Page<Post> search3(Pageable pageable, String contents, String writer, Integer likesCount) {
//        List<Post> result = queryFactory
//                .selectFrom(post)
//                .leftJoin(post.member, member).fetchJoin()
//                .leftJoin(likes.member, member).fetchJoin()
//                .where(contentsEq(contents), writerEq(writer), likesCountEq(likesCount))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//        // 이렇게 해주면 쿼리가 1번만 실행됨, post 입장에서 member는 ToOne이기 때문에 페치 조인으로 쿼리 최적화
//
//
//        // 페이지 수까지 조회해주려면 Page를 반환해주어야하는데 Page를 생성하기 위해서는 총 개수가 필요함
//        Long total = queryFactory.selectFrom(post).fetchCount();
//        return new PageImpl<>(result, pageable, total);
//    }
}
