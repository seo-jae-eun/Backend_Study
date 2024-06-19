package com.example.day01.member.model;

public class MemberSignupRes {
    String email;
    String name;

    public MemberSignupRes() {
    }

    public MemberSignupRes(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
