package com.example.day13.post.model.request;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Getter
public class CreatePostReq {
    @Setter
    private Long memberIdx;
    private String contents;

}
