package com.example.day10.config;

import com.example.day10.websocket.CallHandler;
import com.example.day10.websocket.ChatWebSocketHandler;
import org.checkerframework.checker.units.qual.C;
import org.kurento.client.KurentoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Bean
    public CallHandler callHandler() {
        return new CallHandler();
    }
    @Bean
    public KurentoClient kurentoClient() {
        return KurentoClient.create("ws://192.168.141.200:8888/kurento");
    }
    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(32768);
        return container;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChatWebSocketHandler(), "/chat").setAllowedOrigins("*");
        registry.addHandler(callHandler(), "/call").setAllowedOrigins("*");

    }
}
