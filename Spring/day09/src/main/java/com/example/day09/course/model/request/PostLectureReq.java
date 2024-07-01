package com.example.day09.course.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostLectureReq {
    private String name;
    private Integer playTime;
    private String videoUrl;
}
