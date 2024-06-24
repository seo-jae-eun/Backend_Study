package com.example.jpatest.test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Test {
    @Id
    @GeneratedValue
    private Long id;
    private String str1;

    public Test() {

    }

    public Test(Long id, String str1) {
        this.id = id;
        this.str1 = str1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

}
