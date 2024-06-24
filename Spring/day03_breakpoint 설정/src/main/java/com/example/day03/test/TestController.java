package com.example.day03.test;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public ResponseEntity<String> create() {
        testService.create();
        return ResponseEntity.ok("생성");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity<String> read() {
        testService.read();
        return ResponseEntity.ok("조회");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    public ResponseEntity<String> update() {
        testService.update();
        return ResponseEntity.ok("수정");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public ResponseEntity<String> delete() {
        testService.delete();
        return ResponseEntity.ok("삭제");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/readByIdxAndStr")
    public ResponseEntity<String> readByIdxAndStr() {
        testService.readByIdxAndStr();
        return ResponseEntity.ok("조건 조회");
    }

}
