package org.example.memberservice.adapter.in.web;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupMemberRequest {
    private String email;
    private String name;
}
