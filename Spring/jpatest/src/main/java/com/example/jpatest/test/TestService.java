package com.example.jpatest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Optional<Test> getTestById(Long id) {
        return testRepository.findById(id);
    }

    public Test saveTest(Test test) {
        TransactionSynchronizationManager.getResourceMap();
        Test result = testRepository.save(test);
        return result;
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
