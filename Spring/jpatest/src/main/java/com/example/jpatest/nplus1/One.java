package com.example.jpatest.nplus1;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class One {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;
    @OneToMany(mappedBy = "one", fetch = FetchType.LAZY)
    private List<Many> manyList = new ArrayList<>();

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

    public List<Many> getManyList() {
        return manyList;
    }

    public void setManyList(List<Many> manyList) {
        this.manyList = manyList;
    }
}
