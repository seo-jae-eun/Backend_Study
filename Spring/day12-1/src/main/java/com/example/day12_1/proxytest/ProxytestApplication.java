package com.example.day12_1.proxytest;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProxytestApplication {

    public static void main(String[] args) {
        try {
            System.out.println("메소드 실행 전");
            ProxyImage image01 = new ProxyImage("C:\\upload\\2024\\06\\25\\test.PNG");
            BufferedImage image = image01.getImage();
            System.out.println("메소드 실행 후");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}