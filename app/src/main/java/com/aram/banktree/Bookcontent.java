package com.aram.banktree;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Bookcontent {
    String writer;
    String title;
    ArrayList<String> content;
    ArrayList<Integer> color;
    String date;
    //int []color;
    //Bitmap[]images;
    int page;
    public Bookcontent(String writer, int page){
        this.writer=writer;
        this.page=page;
        content=new ArrayList<String>();
        color=new ArrayList<Integer>();
    }

    public String getTitle() {
        return title;
    }

    public String getWriter(){return writer;}
    public void setTitle(String title) {
        this.title = title;
    }

    public int getpage(){return page;}

    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content.add(content);
    }

    public ArrayList<Integer> getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color.add(color);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    /*public Bitmap[] getImages() {
        return images;
    }

    public void setImages(Bitmap[] images) {
        this.images = images;
    }*/

}
