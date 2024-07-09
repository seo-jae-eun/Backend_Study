package com.example.memberapi.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateMemberRes {
    private Long idx;
    private String email;
}
