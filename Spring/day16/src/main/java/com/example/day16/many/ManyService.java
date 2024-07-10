package com.example.day16.many;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManyService {
    private final ManyRepository manyRepository;

    public ManyService(ManyRepository manyRepository) {
        this.manyRepository = manyRepository;
    }


    public void saveAll(List<Many> manyList) {
        manyRepository.saveAll(manyList);
    }
}
