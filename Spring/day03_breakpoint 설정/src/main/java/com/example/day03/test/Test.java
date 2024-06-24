package com.example.day03.test;

import jakarta.persistence.*;

@Entity
@Table(name="test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

//    @Column(columnDefinition = "varchar(100) not null default ''")
    private String str;

    public Test() {
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
