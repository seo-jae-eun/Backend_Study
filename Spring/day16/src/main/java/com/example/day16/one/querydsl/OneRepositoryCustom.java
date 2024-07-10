package com.example.day16.one.querydsl;

import com.example.day16.one.model.One;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OneRepositoryCustom {
    List<One> findAll();
    List<One> findAllWithMany();
    List<One> findAllWithManyPaging(Pageable pageable);
    List<One> findAllWithManyPaging2(Pageable pageable);

    List<One> findAllWithCriteria(Long idx, String str);
    List<One> findAllWithCriteria2(Long idx, String str);
}
