package com.example.day03.enrollment;

import com.example.day03.lecture.Lecture;
import com.example.day03.student.Student;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name="lecture_idx")
    private Lecture lecture;


    @ManyToOne
    @JoinColumn(name="student_idx")
    private Student student;

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
