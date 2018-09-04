package com.prototype.messageboard;

import java.util.ArrayList;

public class User {

    public String userName;
    public String email;
    private String passwordHint;
    private ArrayList<String> iconInUse;
    private ArrayList<String> iconWaiting;

    public User(){

    }

    public User(String userName, String email, String passwordHint, ArrayList<String> iconInUse, ArrayList<String> iconWaiting){
        this.userName=userName;
        this.email=email;
        this.passwordHint=passwordHint;
        this.iconInUse = iconInUse;
        this.iconWaiting = iconWaiting;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<String> getIconInUse() {
        return iconInUse;
    }

    public void setIconInUse(ArrayList<String> iconInUse) {
        this.iconInUse = iconInUse;
    }

    public ArrayList<String> getIconWaiting() {
        return iconWaiting;
    }

    public void setIconWaiting(ArrayList<String> iconWaiting) {
        this.iconWaiting = iconWaiting;
    }
}
