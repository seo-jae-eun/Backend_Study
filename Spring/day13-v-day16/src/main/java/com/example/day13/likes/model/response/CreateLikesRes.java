package com.example.day13.likes.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLikesRes {
    private Long idx;
    private String result;
}
