package com.example.day03.student;

import jakarta.persistence.*;

@Entity
public class StudentProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
//
//    @OneToOne
//    @JoinColumn(name="student_idx") // 관계에서 주인이 되는 쪽에 추가, 외래키를 관리하는 쪽이 주인
//    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
//
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
}
