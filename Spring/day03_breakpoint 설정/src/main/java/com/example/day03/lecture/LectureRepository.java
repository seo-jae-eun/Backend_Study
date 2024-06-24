package com.example.day03.lecture;


import com.example.day03.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findByLectureName(String lectureName);

}
