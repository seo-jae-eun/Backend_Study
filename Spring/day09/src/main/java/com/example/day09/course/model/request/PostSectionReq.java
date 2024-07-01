package com.example.day09.course.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostSectionReq {
    private String name;
    private List<PostLectureReq> lectures;
}
