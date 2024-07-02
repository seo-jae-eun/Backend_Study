package com.example.day10.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatWebSocketHandler extends TextWebSocketHandler {
    // 세션들을 저장할 변수
//    private final Set<WebSocketSession> sessions = new HashSet<>();
//
//    // 클라이언트가 연결하면 세션 저장 변수에 클라이언트 세션을 추가
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        sessions.add(session);
//        System.out.println(session.getId() + " 클라이언트가 접속했습니다.");
//    }
//
//    // 메시지를 받으면 다른 사람들한테 메시지를 전송
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        for(WebSocketSession s : sessions) {
////            if(s.getId().equals(session.getId())) {
////                continue;
////            }
//            s.sendMessage(new TextMessage(session.getId() + ": " + message.getPayload()));
//        }
//    }
//
//    // 클라이언트가 연결을 끊으면 세션 저장 변수에서 클라이언트 세션을 삭제
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        sessions.remove(session);
//        System.out.println(session.getId() + " 클라이언트가 연결을 끊었습니다.");
//    }

    // 세션들을 저장할 변수
    private final Set<WebSocketSession> sessions = new HashSet<>();
    private final Map<WebSocketSession, String> nicknames = new HashMap<>();

    // 클라이언트가 연결하면 세션 저장 변수에 클라이언트 세션을 추가
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println(session.getId() + " 클라이언트가 접속했습니다.");
        broadcast("현재 접속자 수: " + sessions.size());
    }

    // 메시지를 받으면 다른 사람들한테 메시지를 전송
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        // 닉네임 설정 메시지 형식: /nick 새로운닉네임
        if (payload.startsWith("/nick ")) {
            String newNickname = payload.substring(6).trim();
            nicknames.put(session, newNickname);
            session.sendMessage(new TextMessage("닉네임이 " + newNickname + "(으)로 설정되었습니다."));
        } else {
            String nickname = nicknames.getOrDefault(session, session.getId());
            broadcast(nickname + ": " + payload);
        }
    }

    // 클라이언트가 연결을 끊으면 세션 저장 변수에서 클라이언트 세션을 삭제
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        nicknames.remove(session);
        System.out.println(session.getId() + " 클라이언트가 연결을 끊었습니다.");
        broadcast("현재 접속자 수: " + sessions.size());
    }

    // 모든 세션에 메시지를 broadcast하는 메소드
    private void broadcast(String message) throws Exception {
        for (WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage(message));
        }
    }

}
