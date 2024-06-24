package com.example.day03.enrollment;


import com.example.day03.enrollment.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public ResponseEntity<String> create() {
        enrollmentService.create();
        return ResponseEntity.ok("생성");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read1")
    public ResponseEntity<String> read1(String studentName) {
        enrollmentService.read1(studentName);
        return ResponseEntity.ok("조회1");
    }
    @RequestMapping(method = RequestMethod.GET, value = "/read2")
    public ResponseEntity<String> read2(String lectureName) {
        enrollmentService.read2(lectureName);
        return ResponseEntity.ok("조회2");
    }
    @RequestMapping(method = RequestMethod.GET, value = "/read3")
    public ResponseEntity<String> read3() {
        enrollmentService.read3();
        return ResponseEntity.ok("조회3");
    }
    @RequestMapping(method = RequestMethod.GET, value = "/read4")
    public ResponseEntity<String> read4() {
        enrollmentService.read4();
        return ResponseEntity.ok("조회4");
    }

}