package com.example.day03.test;

import jakarta.persistence.*;

@Entity
//@Table(name="테이블이름")
public class Test { // 테이블 이름
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AI
    private Long idx; // 컬럼

//    @Column(nullable = false) // NOT NULL
//    @Column(columnDefinition = "varchar(100) not null default=''")
//    @Column(unique = true)
    private String str;

    public Test() {
    }

    public Test(String str) {
        this.str = str;
    }

    public Test(Long idx, String str) {
        this.idx = idx;
        this.str = str;
    }


    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
