package com.example.day02.board.model;

public class BoardReadRes {
    private String title;
    private String contents;
    private String writer;

    public BoardReadRes(String title, String contents, String writer) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
