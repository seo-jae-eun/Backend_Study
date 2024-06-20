package com.example.day02.board.model;

public class BoardCreateReq {
    private String title;
    private String contents;

    public BoardCreateReq() {
    }

    public BoardCreateReq(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
