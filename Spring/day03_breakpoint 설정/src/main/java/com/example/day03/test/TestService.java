package com.example.day03.test;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }
    public void create() {
        Test test = new Test();
        test.setStr("test01");
        testRepository.save(test);
    }
    public void read() {
        testRepository.findById(1L);
    }
    public void update() {
        Test test = new Test();
        test.setIdx(1L);
        test.setStr("test02");
        testRepository.save(test);
    }
    public void delete() {
        testRepository.deleteById(1L);
    }

    public void readByIdxAndStr() {
        testRepository.findByIdxAndStr(1L, "test01");
    }
}
