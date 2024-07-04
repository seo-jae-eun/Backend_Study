package com.example.day12.test;

import com.example.day12.common.annotation.Timer;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Timer
    public void ex01() {
        int num01 = 10;
        int result = num01 / 2;

    }
}
