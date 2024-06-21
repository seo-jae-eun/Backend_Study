package com.example.day03.student;

import jakarta.persistence.*;

@Entity
public class StudentProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String imageUrl;

    // 1:1일때 조회가 잘 안되는 곳에 외래키를 만든다.
    // 외래키처럼 동작하지만 DB의 외래키와 1:1 매핑되는 것은 아님, 정확하게 동작하지 않음
    // 1:1 만들 때는 양쪽에 만들어줘야됨, 한쪽에만 만들어주면 Student 입장에서는 누구랑 관련되어있는지 모름, 그래서 양쪽에 OneToOne 해줌
//    @OneToOne(fetch = FetchType.LAZY)
    @OneToOne // 근데 그러면 어느 쪽에 외래키가 있는지 모르니까 다시 표시해줌
    @JoinColumn(name = "student_idx") // -> 외래키 이름
    private Student student;


    public StudentProfileImage() {
    }

    public StudentProfileImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
