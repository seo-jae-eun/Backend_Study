package com.example.day01.member.model;

// 스프링 제공 x, 그냥 자바 클래스
public class MemberSignupReq {
    String email;
    String password;
    String name;

    // 매개변수 없는 생성자
    public MemberSignupReq() {
    }

    // 모든 매개변수가 있는 생성자
    public MemberSignupReq(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }


    // Getter, Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
