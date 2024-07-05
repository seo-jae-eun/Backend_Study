package com.example.day13.member.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateMemberRes {
    private Long idx;
    private String email;
}
