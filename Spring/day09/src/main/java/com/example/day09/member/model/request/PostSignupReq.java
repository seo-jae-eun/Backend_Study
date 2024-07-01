package com.example.day09.member.model.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSignupReq {
    private String email;
    private String password;
    private String name;
}
