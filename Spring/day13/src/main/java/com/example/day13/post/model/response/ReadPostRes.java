package com.example.day13.post.model.response;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class ReadPostRes {
    private Long idx;
    private String contents;

    private ReadMemberRes writer;
    private List<ReadLikesRes> likesList;
}