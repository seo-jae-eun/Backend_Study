package com.example.day03.student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/create")
    public ResponseEntity<String> create() {
        studentService.create();
        return ResponseEntity.ok("생성");
    }
    @RequestMapping("/read")
    public ResponseEntity<String> read() {
        studentService.read();
        return ResponseEntity.ok("조회");
    }
    @RequestMapping("/update")
    public ResponseEntity<String> update() {
        studentService.update();
        return ResponseEntity.ok("수정");
    }
    @RequestMapping("/delete")
    public ResponseEntity<String> delete() {
        studentService.delete();
        return ResponseEntity.ok("삭제");
    }
}
