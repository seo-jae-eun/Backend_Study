package com.example.day03.student;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentProfileImageRepository studentProfileImageRepository;

    public StudentService(StudentRepository studentRepository, StudentProfileImageRepository studentProfileImageRepository) {
        this.studentRepository = studentRepository;
        this.studentProfileImageRepository = studentProfileImageRepository;
    }

    @Transactional
    public void create() {
        Student student = new Student();
        student.setName("student01");
        studentRepository.save(student);
        StudentProfileImage studentProfileImage = new StudentProfileImage();
        studentProfileImage.setImageUrl("url01");
//        studentProfileImage.setStudent(student);
        studentProfileImageRepository.save(studentProfileImage);
    }

    public void read() {
//        Student student = studentRepository.findById(1L).orElseThrow();
        Optional<Student> result = studentRepository.findById(1L);
        Student student = result.get();
        System.out.println(student.getName());

        Optional<Student> result2 = studentRepository.findById(1L);
        Student student2 = result2.get();
        System.out.println(student2.getName());
//        System.out.println(student.getStudentProfileImage().getImageUrl());
    }


    @Transactional
    public void update() {
        Student student = new Student();
        student.setIdx(1L);
        student.setName("학생02");
        studentRepository.save(student);

        System.out.println();
        System.out.println();
        System.out.println();
    }
    public void delete() {
        studentRepository.deleteById(1L);
    }

}
