package com.example.jpatest.nplus1;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Many {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;
    @ManyToOne
    @JoinColumn(name = "one_idx")
    private One one;

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

    public One getOne() {
        return one;
    }

    public void setOne(One one) {
        this.one = one;
    }
}
