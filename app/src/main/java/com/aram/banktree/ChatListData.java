package com.aram.banktree;

//내가 연락한 사람들의 목록을 저장하기 위한 class
public class ChatListData {
    private String nickname;

    public ChatListData(String nickname){
        this.nickname=nickname;
    };


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
