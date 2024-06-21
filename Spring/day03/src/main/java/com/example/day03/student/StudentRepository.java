package com.example.day03.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    // 내꺼
//    List<Student> findByTeamIdx(Long teamIdx);

    // 강사님꺼
    Optional<Student> findByName(String studentName);
}
