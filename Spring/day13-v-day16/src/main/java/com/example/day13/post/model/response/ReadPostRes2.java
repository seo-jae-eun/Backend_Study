package com.example.day13.post.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ReadPostRes2 {
    private Long idx;
    private String contents;
    private String writer;
    private Integer likesCount;

    private List<String> likesUserEmail;
}
