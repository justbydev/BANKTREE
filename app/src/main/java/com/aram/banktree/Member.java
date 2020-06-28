package com.aram.banktree;
//Member class는 회원가입 할 때 회원 정보를 저장하는 class로
//이 Member class를 통해 firebase 서버에 회원 정보를 저장하게 된다
public class Member {
    String email;
    String name;
    String gender;
    int year;
    int month;
    int day;

    public Member() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
