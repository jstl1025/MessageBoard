package com.prototype.messageboard;

import java.util.ArrayList;

public class User {

    public String userName;
    public String email;
    private String passwordHint;
    private ArrayList<String> iconPaths;

    public User(){

    }

    public User(String userName, String email, String passwordHint, ArrayList<String> iconPaths){
        this.userName=userName;
        this.email=email;
        this.passwordHint=passwordHint;
        this.iconPaths=iconPaths;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<String> getIconPaths() {
        return iconPaths;
    }

    public void setIconPaths(ArrayList<String> iconPaths) {
        this.iconPaths = iconPaths;
    }
}
