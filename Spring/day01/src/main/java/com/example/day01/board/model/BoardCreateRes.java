package com.example.day01.board.model;

// 응답 모델 (게시글 제목)
public class BoardCreateRes {
    String title;

    public BoardCreateRes() {
    }

    public BoardCreateRes(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
