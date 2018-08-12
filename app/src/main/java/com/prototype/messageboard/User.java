package com.prototype.messageboard;

public class User {

    public String userName;
    public String email;
    private String passwordHint;

    public User(){

    }

    public User(String userName, String email, String passwordHint){
        this.userName=userName;
        this.email=email;
        this.passwordHint=passwordHint;
    }

    public String getUserName() {
        return userName;
    }
}
