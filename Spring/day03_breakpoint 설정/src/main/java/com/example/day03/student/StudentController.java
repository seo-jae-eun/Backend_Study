package com.example.day03.student;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public ResponseEntity<String> create() {
        studentService.create();
        return ResponseEntity.ok("생성");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity<String> read() {
        studentService.read();
        return ResponseEntity.ok("조회");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    public ResponseEntity<String> update() {
        studentService.update();
        return ResponseEntity.ok("수정");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public ResponseEntity<String> delete() {
        studentService.delete();
        return ResponseEntity.ok("삭제");
    }

}
