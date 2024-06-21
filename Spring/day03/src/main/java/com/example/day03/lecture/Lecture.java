package com.example.day03.lecture;

import com.example.day03.enrollment.Enrollment;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String lectureName;

    @OneToMany(mappedBy = "lecture")
    private List<Enrollment> enrollments = new ArrayList<>();

    public Lecture() {
    }

    public Lecture(String lectureName) {
        this.lectureName = lectureName;
    }


    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
