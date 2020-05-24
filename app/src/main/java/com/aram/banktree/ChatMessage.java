package com.aram.banktree;

public class ChatMessage {
    private String text;
    private String name;

    public ChatMessage() {
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
