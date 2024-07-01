package com.example.day09.course;

import com.example.day09.common.BaseResponse;
import com.example.day09.course.model.Course;
import com.example.day09.course.model.Lecture;
import com.example.day09.course.model.Section;
import com.example.day09.course.model.request.PostCourseReq;
import com.example.day09.course.model.request.PostLectureReq;
import com.example.day09.course.model.request.PostSectionReq;
import com.example.day09.course.model.response.GetCourseDetailRes;
import com.example.day09.course.model.response.PostCourseRes;
import com.example.day09.member.model.Member;
import com.example.day09.member.model.response.PostSignupRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final SectionRepository sectionRepository;
    private final LectureRepository lectureRepository;

    public BaseResponse<PostCourseRes> create(PostCourseReq dto, String fileName) {
        // Course 생성
        Course course = Course.builder()
                .name(dto.getName())
                .createdAt(LocalDateTime.now())
                .image(fileName)
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();

        // Course 저장
        course = courseRepository.save(course);

        // Sections 저장
        for (PostSectionReq sectionDto : dto.getSections()) {
            Section section = Section.builder()
                    .name(sectionDto.getName())
                    .createdAt(LocalDateTime.now())
                    .course(course)
                    .build();

            // Section 저장
            section = sectionRepository.save(section);

            // Lectures 저장
            for (PostLectureReq lectureDto : sectionDto.getLectures()) {
                Lecture lecture = Lecture.builder()
                        .name(lectureDto.getName())
                        .createdAt(LocalDateTime.now())
                        .playTime(lectureDto.getPlayTime())
                        .videoUrl(lectureDto.getVideoUrl())
                        .section(section)
                        .build();

                // Lecture 저장
                lectureRepository.save(lecture);

                // Section에 Lecture 추가
                section.getLectureList().add(lecture);
            }

            // Course에 Section 추가
            course.getSectionList().add(section);
        }

        courseRepository.save(course);


        BaseResponse<PostCourseRes> response = new BaseResponse<>(new PostCourseRes(course.getId(), course.getName(), course.getImage(), course.getDescription(), course.getPrice()));
        return response;
    }

    public List<PostCourseRes> list() {
        List<Course> courseList = courseRepository.findAll();

        List<PostCourseRes> courseResList = new ArrayList<>();

        for (Course course : courseList) {
            PostCourseRes postCourseRes = course.toDto();
            courseResList.add(postCourseRes);
        }

        return courseResList;
    }


//    public GetCourseDetailRes detail(Long id) {
//        Optional<Course> result = courseRepository.findById(id);
//        Course course = result.get();
//
//        GetCourseDetailRes courseDetailRes = course.toDto();
//        return courseDetailRes;
//
//    }
}
