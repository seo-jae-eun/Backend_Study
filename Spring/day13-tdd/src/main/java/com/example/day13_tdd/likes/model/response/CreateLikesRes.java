package com.example.day13_tdd.likes.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLikesRes {
    private Long idx;
    private String resullt;
}
