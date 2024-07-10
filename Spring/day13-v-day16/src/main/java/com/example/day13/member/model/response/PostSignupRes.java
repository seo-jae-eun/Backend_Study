package com.example.day13.member.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostSignupRes {
    private Long idx;
    private String email;
}

