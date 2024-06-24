package com.example.day03.enrollment;

import com.example.day03.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
