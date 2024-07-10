package com.example.day13.post.model.requset;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostReq {
    private String contents;
}
