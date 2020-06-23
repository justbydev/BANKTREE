package com.aram.banktree;

import java.util.ArrayList;

public class Totalbook {
    private String title;
    private String writer;
    private ArrayList<String> content;
    private ArrayList<String> color;
    private String page;

    public Totalbook() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(ArrayList<String> content) {
        this.content = content;
    }

    public ArrayList<String> getColor() {
        return color;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setColor(ArrayList<String> color) {
        this.color = color;
    }
}
