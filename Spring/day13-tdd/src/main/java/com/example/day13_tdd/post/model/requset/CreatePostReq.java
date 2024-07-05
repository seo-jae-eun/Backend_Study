package com.example.day13_tdd.post.model.requset;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostReq {
    private String contents;
}
