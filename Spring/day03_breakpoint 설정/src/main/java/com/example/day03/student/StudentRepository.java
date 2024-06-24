package com.example.day03.student;

import com.example.day03.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByName(String studentName);
}
