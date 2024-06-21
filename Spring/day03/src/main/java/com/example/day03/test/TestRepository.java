package com.example.day03.test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> { // 테이블, 기본키 타입
    Optional<Test> findByIdxAndStr(Long idx, String str);
}
