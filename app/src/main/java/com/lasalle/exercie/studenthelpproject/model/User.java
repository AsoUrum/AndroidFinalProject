package com.lasalle.exercie.studenthelpproject.model;

import androidx.annotation.NonNull;

public class User {

    private  int userId;
    private  String userName;
    private  String password;
    private  String title;

    public User() {
    }

    public User(int userid, String userName, String password, String title) {
        this.userId = userid ;
        this.userName = userName;
        this.password = password;
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }


    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
