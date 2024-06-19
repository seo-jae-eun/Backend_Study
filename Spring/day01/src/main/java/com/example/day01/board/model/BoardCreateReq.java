package com.example.day01.board.model;

// 요청 모델 (게시글 제목, 게시글 내용)
public class BoardCreateReq {
    String title;
    String content;

    public BoardCreateReq() {
    }

    public BoardCreateReq(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
