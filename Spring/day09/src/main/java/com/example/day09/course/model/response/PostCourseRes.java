package com.example.day09.course.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostCourseRes {
    private Long id;
    private String name;
    private String image;
    private String description;
    private Integer price;
}
