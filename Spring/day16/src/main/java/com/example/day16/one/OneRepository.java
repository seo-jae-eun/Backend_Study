package com.example.day16.one;

import com.example.day16.one.model.One;
import com.example.day16.one.model.OneDto;
import com.example.day16.one.querydsl.OneRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OneRepository extends JpaRepository<One, Long>, OneRepositoryCustom {
    // 엔티티와 속성은 대소문자를 구분한다. (One(엔티티), idx(속성))
    // JPQL 키워드는 대소문자를 구분하지 않는다. (Select, FROM, where)
    // 테이블 이름이 아닌 엔티티 이름을 사용한다. (One)
    // 별칭(o) 사용은 필수적이다.
    // as는 생략이 가능하다.
    @Override
    @Query("SELECT o FROM One o")
    public List<One> findAll();

    // 자바의 변수 값을 사용할 때는 :을 앞에 붙인다.
    @Query("SELECT o FROM One o WHERE o.idx > :oneIdx")
    // 이름 같으면 @Param 생략 가능
    public List<One> findAllByIdxGreaterThan(@Param("oneIdx") Long oneIdx);

    // FETCH : 가져오다, One 가져오면서 o.manyList도 같이 가져와라
    @Query("SELECT o FROM One o JOIN FETCH o.manyList")
    List<One> findAllOneWithMany();

//    @Query("SELECT o FROM One o JOIN FETCH o.manyList")
//    List<One> findAllBy(Integer page, Integer size);


    @Query("SELECT new com.example.day16.one.model.OneDto(o.idx, o.str, m.str) FROM One o JOIN o.manyList m")
    List<OneDto> findAllOneDto();


    Page<One> findPageBy(Pageable pageable);

    Slice<One> findSliceBy(Pageable pageable);


    @EntityGraph(attributePaths = {"manyList"}) // 스프링 데이터 JPA 에서 FETCH 조인을 편하게 하기 위한 설정, 현재 조회하는 엔티티에 있는 연관된 컬렉션을 명시적으로 FETCH 조인하도록 지정한 것
    List<One> findAllEntityGraphBy();
    // 페치 조인이랑 비슷


}
