package com.example.day09.member.model.response;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSignupRes {
    private Long id;
    private String email;
    private String name;
}
