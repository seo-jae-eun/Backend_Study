package com.example.day13.post.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ListPostRes {
    private Long idx;
    private String contents;

}
