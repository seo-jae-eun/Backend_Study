package com.example.day09.course;

import com.example.day09.common.BaseResponse;
import com.example.day09.course.model.request.PostCourseReq;
import com.example.day09.course.model.response.GetCourseDetailRes;
import com.example.day09.course.model.response.PostCourseRes;
import com.example.day09.file.FileUploadService;
import com.example.day09.member.model.CustomUserDetails;
import com.example.day09.member.model.response.PostSignupRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final FileUploadService fileUploadService;


    @RequestMapping("/create")
    public ResponseEntity<BaseResponse<PostCourseRes>> create(@RequestPart MultipartFile image, @RequestPart PostCourseReq request) {
        String fileName = fileUploadService.upload(image);

        BaseResponse<PostCourseRes> response = courseService.create(request, fileName);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<BaseResponse<List<PostCourseRes>>> list() {
        List<PostCourseRes> courseResList = courseService.list();

        return ResponseEntity.ok(new BaseResponse<>(courseResList));
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
//    public BaseResponse<GetCourseDetailRes> readCourse(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long id) {
//        courseService.detail(id);
//    }
}
