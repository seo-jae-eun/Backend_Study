package com.example.day13.member.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateMemberReq {
    private String email;
    private String password;
}
