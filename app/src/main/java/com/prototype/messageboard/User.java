package com.prototype.messageboard;

public class User {

    public String userName;
    private String password;
    private String passwordHint;

    public User(){

    }

    public User(String userName){
        this.userName=userName;
        this.password=password;
        this.passwordHint=passwordHint;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setPasswordHint(String passwordHint){
        this.passwordHint=passwordHint;
    }

    public String getPasswordHint(){
        return this.passwordHint;
    }
}
