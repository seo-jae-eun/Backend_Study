package com.example.day16.one.model;

import com.example.day16.many.Many;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class One {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String str;

    @Builder.Default        // Builder 패턴에서 기본값을 줄 때 사용
    @BatchSize(size = 10)   // 쿼리 최적화, 이 크기 만큼씩만 가져올 때
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
