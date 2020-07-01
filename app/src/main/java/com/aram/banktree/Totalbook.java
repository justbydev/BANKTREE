package com.aram.banktree;

import java.util.ArrayList;
//Totalbook class는 Splash에서 firebase로부터 전자책을 받아올 때 하나 하나 저장하는 class
//또한 새로운 책을 등록할 때 사용하는 class
public class Totalbook {
    private String title;
    private String writer;
    private ArrayList<String> content;
    private ArrayList<String> color;
    private String page;
    private String date;
    private int cat;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }
}
