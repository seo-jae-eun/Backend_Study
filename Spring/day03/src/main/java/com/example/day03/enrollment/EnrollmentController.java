package com.example.day03.enrollment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @RequestMapping("/create")
    public ResponseEntity<String> create() {
        enrollmentService.create();
        return ResponseEntity.ok("생성");
    }
    @RequestMapping("/read1")
    public ResponseEntity<String> read1(String studentName) {
        enrollmentService.read1(studentName);
        return ResponseEntity.ok("조회1");
    }
}
