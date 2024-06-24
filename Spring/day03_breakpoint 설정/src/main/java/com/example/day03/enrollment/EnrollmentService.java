package com.example.day03.enrollment;

import com.example.day03.enrollment.Enrollment;
import com.example.day03.enrollment.EnrollmentRepository;
import com.example.day03.lecture.Lecture;
import com.example.day03.lecture.LectureRepository;
import com.example.day03.student.Student;
import com.example.day03.student.StudentRepository;
import com.example.day03.team.Team;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
        for (int i = 0; i < lectures.size(); i++) {
            Lecture lecture = new Lecture();
            lecture.setLectureName(lectures.get(i));
            lectureRepository.save(lecture);

            List<Student> students = studentRepository.findAll();
            for (int j = 0; j < (int) (Math.random() * 10) + 1; j++) {
                Enrollment enrollment = new Enrollment();
                enrollment.setLecture(lecture);
                Student student = new Student();
                student.setIdx((int) (Math.random() * 27)+1L);
                enrollment.setStudent(student);


                enrollmentRepository.save(enrollment);
            }
        }

    }

    public void read1(String studentName) {

        Student student = studentRepository.findByName(studentName).orElseThrow();

        for (Enrollment enrollment: student.getEnrollments()) {
            System.out.println(student.getName() + " " + enrollment.getLecture().getLectureName());
        }
    }

    public void read2(String lectureName) {
        Lecture lecture = lectureRepository.findByLectureName(lectureName).orElseThrow();

        for (Enrollment enrollment: lecture.getEnrollments()) {
            System.out.println(lecture.getLectureName() + " " + enrollment.getStudent().getName());
        }
    }

    public void read3() {
        List<Lecture> lectures = lectureRepository.findAll();
        for (Lecture lecture: lectures) {
            for (Enrollment enrollment: lecture.getEnrollments()) {
                System.out.println(lecture.getLectureName() + " " + enrollment.getStudent().getName());
            }
        }
    }

    public void read4() {
        List<Student> students = studentRepository.findAll();

        for (Student student: students) {
            for (Enrollment enrollment: student.getEnrollments()) {
                System.out.println(student.getName() + " " + enrollment.getLecture().getLectureName());
            }
        }
    }


}
