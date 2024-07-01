package com.example.day09.course.model.request;

import com.example.day09.course.model.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
//@Builder
public class PostCourseReq {
    private String name;
    private String description;
    private Integer price;

    private List<PostSectionReq> sections;
}
