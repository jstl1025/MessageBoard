package com.prototype.messageboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SettingActivity extends AppCompatActivity {

//create new activity page: DisplayCustomImg
    public void customImgFunction(View view){
        Intent startCustomImgActivity = new Intent(this, DisplayCustomImgActivity.class);
        startActivity(startCustomImgActivity);
    }

// create new activity page: DisplaySetActivity
    public void displaySetFunction(View view){
        Intent startDisplaySetActivity = new Intent(this, DisplaySetActivity.class);
        startActivity(startDisplaySetActivity);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // sets night theme defined in styles
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
