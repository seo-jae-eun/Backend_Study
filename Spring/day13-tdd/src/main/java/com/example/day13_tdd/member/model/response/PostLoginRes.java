package com.example.day13_tdd.member.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostLoginRes {
    private String accessToken;
}