package com.example.day03.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface TestRepository extends JpaRepository<Test, Long> {
    Optional<Test> findByIdxAndStr(Long idx, String str);
}
