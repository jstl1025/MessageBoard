package com.prototype.messageboard;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Pop extends Activity {
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        //position
        getWindow().setGravity(Gravity.TOP);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        //dimension
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        Integer imageButtonVal;
        Bundle extras = getIntent().getExtras();
        imageButtonVal = extras.getInt("imageButton");


        if (imageButtonVal == 1){
            params.x = -280;
            params.y = 360;
            window.setAttributes(params);
            width = width /5;
            height = height/9;
            window.setLayout(width,height);
        }
        else if (imageButtonVal == 2){
            params.x = 280;
            params.y = 360;
            window.setAttributes(params);
            width = width /5;
            height = height/9;
            window.setLayout(width,height);
        }
        else if (imageButtonVal == 3){
            params.x = -280;
            params.y = 755;
            window.setAttributes(params);
            width = width /5;
            height = height/9;
            window.setLayout(width,height);
        }
        else if (imageButtonVal == 4){
            params.x = 280;
            params.y = 755;
            window.setAttributes(params);
            width = width /5;
            height = height/9;
            window.setLayout(width,height);
        }
        else if (imageButtonVal == 5){
            params.x = -280;
            params.y = 1150;
            window.setAttributes(params);
            width = width /5;
            height = height/9;
            window.setLayout(width,height);
        }
        else if (imageButtonVal == 6){
            params.x = 280;
            params.y = 1150;
            window.setAttributes(params);
            width = width /5;
            height = height/9;
            window.setLayout(width,height);
        }


        addListenerOnButton();

    }

    public void addListenerOnButton(){
        sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // remove activity after click
            finish();
        }

    });

    }


}
