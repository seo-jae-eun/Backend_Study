package com.example.day13_tdd.post.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReadPostRes {
    private Long idx;
    private String contents;
    private String writer;
    private Integer likesCount;
}
