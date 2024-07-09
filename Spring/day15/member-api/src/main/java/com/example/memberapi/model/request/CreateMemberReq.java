package com.example.memberapi.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateMemberReq {
    private String email;
    private String password;
}
