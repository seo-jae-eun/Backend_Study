package com.example.day16.one.querydsl;

import com.example.day16.many.QMany;
import com.example.day16.one.model.One;
import com.example.day16.one.model.QOne;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OneRepositoryCustomImpl implements OneRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private BooleanBuilder builder;
    private QOne one;


    public OneRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.builder = new BooleanBuilder();
        this.one = QOne.one;
    }

    @Override
    public List<One> findAll() {
        QOne one = QOne.one;
                                        // SELECT * FROM One;
        List<One> result = queryFactory.selectFrom(one).fetch();
        return result;
    }

    @Override
    public List<One> findAllWithMany() {
        QOne one = QOne.one;
        QMany many = QMany.many;

        List<One> result = queryFactory.selectFrom(one).leftJoin(one.manyList, many).fetchJoin().fetch();
        return result;
    }

    @Override
    public List<One> findAllWithManyPaging(Pageable pageable) {
        QOne one = QOne.one;
        QMany many = QMany.many;

        List<One> result = queryFactory
                .selectFrom(one)
                .leftJoin(one.manyList, many).fetchJoin()
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return result;
    }

    @Override
    public List<One> findAllWithManyPaging2(Pageable pageable) {
        QOne one = QOne.one;

        List<One> result = queryFactory
                .selectFrom(one)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return result;
    }


    @Override
    public List<One> findAllWithCriteria(Long idx, String str) {

        // SELECT * FROM One WHERE idx=?
//        if(idx != null) {
//            builder.and(one.idx.eq(idx));
//        }
//
//        // SELECT * FROM One WHERE idx=? AND str=?
//        if(str != null) {
//            builder.and(one.str.eq(str));
//        }

        List<One> result = queryFactory
                .selectFrom(one)
                .where(idxEq(idx), strEq(str))
                .fetch();

        return result;
    }

    private BooleanExpression idxEq(Long idx) {
        if(idx == null) {
            return null;
        }
        return one.idx.eq(idx);
    }

    private BooleanExpression strEq(String str) {
        if(str == null) {
            return null;
        }
        return one.str.eq(str);
    }

    @Override
    public List<One> findAllWithCriteria2(Long idx, String str) {

        // SELECT * FROM One WHERE idx=?
        if(idx != null) {
            builder.and(one.idx.eq(idx));
        }

        // SELECT * FROM One WHERE idx=? AND str=?
        if(str != null) {
            builder.and(one.str.eq(str));
        }

        List<One> result = queryFactory
                .selectFrom(one)
                .where(builder)
                .fetch();

        return result;
    }

}
