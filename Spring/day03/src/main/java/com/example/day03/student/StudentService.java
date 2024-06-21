package com.example.day03.student;

import com.example.day03.test.Test;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentProfileImageRepository studentProfileImageRepository;

    public StudentService(StudentRepository studentRepository, StudentProfileImageRepository studentProfileImageRepository) {
        this.studentRepository = studentRepository;
        this.studentProfileImageRepository = studentProfileImageRepository;
    }


    public void create() {
        Student student = new Student("서재은", 25); // student에 idx값 없음
        studentRepository.save(student); // student에 idx값 생김
        StudentProfileImage studentProfileImage = new StudentProfileImage("url01");
//        studentProfileImage.setStudent(student);
        studentProfileImageRepository.save(studentProfileImage);
    }

    public void read() {
        // 조회했을 때 값이 없을 때를 처리해주기 위해 Optional 사용
//        Student student = studentRepository.findById(1L).orElseThrow();
        Optional<Student> result = studentRepository.findById(1L);
        Student student = result.get();
        System.out.println(student.getName());
//        System.out.println(student.getStudentProfileImage().getImageUrl());
    }

    public void update() {
    }

    public void delete() {
    }

}
