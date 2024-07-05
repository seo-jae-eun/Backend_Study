package com.example.day13.likes.model.response;

import com.example.day13.member.model.Member;
import com.example.day13.post.model.Post;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateLikesRes {
    private String status;
    private Long likesIdx;

}
