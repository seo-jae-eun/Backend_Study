package com.example.day13_tdd.member.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostSignupRes {
    private Long id;
    private String email;
}

