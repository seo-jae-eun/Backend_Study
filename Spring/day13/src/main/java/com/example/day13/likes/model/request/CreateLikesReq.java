package com.example.day13.likes.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateLikesReq {
    private Long memberIdx;
    private Long postIdx;
}
