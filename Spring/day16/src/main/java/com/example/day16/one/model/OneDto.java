package com.example.day16.one.model;

public class OneDto {
    private Long idx;
    private String str;
    private String manyStr;

    public OneDto(Long idx, String str, String manyStr) {
        this.idx = idx;
        this.str = str;
        this.manyStr = manyStr;
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

    public String getManyStr() {
        return manyStr;
    }

    public void setManyStr(String manyStr) {
        this.manyStr = manyStr;
    }
}
