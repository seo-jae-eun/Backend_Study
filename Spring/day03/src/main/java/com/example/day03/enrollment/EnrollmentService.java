package com.example.day03.enrollment;

import com.example.day03.lecture.Lecture;
import com.example.day03.lecture.LectureRepository;
import com.example.day03.student.Student;
import com.example.day03.student.StudentRepository;
import com.example.day03.team.Team;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, LectureRepository lectureRepository, StudentRepository studentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.lectureRepository = lectureRepository;
        this.studentRepository = studentRepository;
    }

    public void create() {
        List<String> lectures = Arrays.asList("DB", "리눅스", "자바", "자료구조", "알고리즘", "스프링");

        for(String name : lectures) {
            Lecture lecture = new Lecture(name);
            lectureRepository.save(lecture);
            int enrollmentNum = (int) (Math.random() * 10) + 1;
            for(int i = 0; i < enrollmentNum; i++) {
                // 중복체크 필요
                int studentNum = (int) (Math.random() * 27) + 1;
                Student student = studentRepository.findById((long)studentNum).get();
                Enrollment enrollment = new Enrollment(student, lecture);
                enrollment.setStudent(student);
                enrollmentRepository.save(enrollment);
            }
        }
    }

    // SELECT lecture_name FROM
    // 학생의 이름을 받아서 학생의 이름과 학생이 수강중인 수업들의 이름을 출력하는 메소드
    public void read1(String studentName) {
//        Long studentIdx = studentRepository.findByName(studentName).get().getIdx();
//        System.out.println(studentName);
//
//        Long lectureIdx = enrollmentRepository.findByStudentIdx(studentIdx).get().getIdx();

        Optional<Student> result = studentRepository.findByName(studentName);
        Student student = result.get();


        for (Enrollment enrollment : student.getEnrollments()) {
            Optional<Enrollment> result1 = enrollmentRepository.findById(enrollment.getIdx());
            Enrollment enrollment1 = result1.get();
            System.out.println(enrollment1.getLecture().getLectureName());
        }

    }
}
