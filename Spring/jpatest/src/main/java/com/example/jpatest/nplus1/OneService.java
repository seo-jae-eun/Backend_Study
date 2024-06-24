package com.example.jpatest.nplus1;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OneService {
   private final OneRepository oneRepository;

    public OneService(OneRepository oneRepository) {
        this.oneRepository = oneRepository;
    }

    public void saveAll(List<One> oneList) {
        oneRepository.saveAll(oneList);
    }

    public List<One> findAll() {

        return oneRepository.findAll();
    }
}
