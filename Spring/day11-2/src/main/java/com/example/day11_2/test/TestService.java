package com.example.day11_2.test;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @KafkaListener(topics = "a", groupId = "group_1")
    public void receive(String message) {
        System.out.println(message);
    } // 얘가 서비스에 있는게 맞나? 컨트롤러에서 들어온 요청이 아님.. -> CS를 나눌 필요가 있을까? -> 클린 아키텍처
}
