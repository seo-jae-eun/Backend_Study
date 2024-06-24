package com.example.jpatest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable Long id) {
        Optional<Test> test = testService.getTestById(id);
        if (test.isPresent()) {
            return ResponseEntity.ok(test.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Test createTest(@RequestBody Test test) {
        return testService.saveTest(test);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable Long id, @RequestBody Test testDetails) {
        Optional<Test> test = testService.getTestById(id);
        if (test.isPresent()) {
            Test testToUpdate = test.get();
            testToUpdate.setStr1(testDetails.getStr1());
            Test updatedTest = testService.saveTest(testToUpdate);
            return ResponseEntity.ok(updatedTest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
        return ResponseEntity.noContent().build();
    }
}
