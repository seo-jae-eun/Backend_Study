package com.example.day02.board.model;

public class BoardCreateRes {
    private String title;

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
