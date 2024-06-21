package com.example.day03.student;

import com.example.day03.enrollment.Enrollment;
import com.example.day03.lecture.Lecture;
import com.example.day03.team.Team;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;
    private Integer age;


    @OneToOne(mappedBy = "student") // 주인 설정
//    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY)
    private StudentProfileImage studentProfileImage;


    @ManyToOne
    @JoinColumn(name = "team_idx")
    private Team team;


    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments = new ArrayList<>();



    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public StudentProfileImage getStudentProfileImage() {
        return studentProfileImage;
    }

    public void setStudentProfileImage(StudentProfileImage studentProfileImage) {
        this.studentProfileImage = studentProfileImage;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
