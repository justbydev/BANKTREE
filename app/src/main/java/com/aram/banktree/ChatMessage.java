package com.aram.banktree;

//개인 채팅방에서 대화 하나 하나에 대한 class
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
