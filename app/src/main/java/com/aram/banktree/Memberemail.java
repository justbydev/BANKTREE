package com.aram.banktree;

//앱에 회원가입 되어 있는 이메일 계정만 따로 관리하기 위해서 만든 class
public class Memberemail {
    String email;
    public Memberemail(){

    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
}
