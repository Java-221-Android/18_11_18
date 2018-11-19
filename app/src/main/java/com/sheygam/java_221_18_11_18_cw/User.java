package com.sheygam.java_221_18_11_18_cw;

public class User {
    private String title, email;

    public User() {
    }

    public User(String title, String email) {
        this.title = title;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
