package com.example.day11.websocket;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {
    // 세션들을 저장할 변수
    private final Set<WebSocketSession> sessions = new HashSet<>();
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ChatWebSocketHandler(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // 클라이언트가 연결하면 세션 저장 변수에 클라이언트 세션을 추가
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println(session.getId() + " 클라이언트가 접속했습니다.");
    }

    // 메시지를 받으면 다른 사람들한테 메시지를 전송
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 카프카한테 보내기
        System.out.println("보낸 메시지: " + message.getPayload());
        kafkaTemplate.send("chat", message.getPayload());
    }
    
    // 카프카한테 메시지 받는 코드
    @KafkaListener(topics = "chat", groupId = "group_1")
    public void receive(String message) throws IOException {
        System.out.println("받는 메시지: " + message);
        for(WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage(message));
        }
    }


    // 클라이언트가 연결을 끊으면 세션 저장 변수에서 클라이언트 세션을 삭제
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println(session.getId() + " 클라이언트가 연결을 끊었습니다.");
    }

}
