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
import android.widget.Toast;

public class Pop extends Activity{
    Button sendButton1;

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
        params.x = -280;
        params.y = 360;
        window.setAttributes(params);

        //dimension
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        width = width /5;
        int height = dm.heightPixels;
        height = height/9;
        window.setLayout(width,height);

        addListenerOnButton();

    }

    public void addListenerOnButton(){
        sendButton1 = (Button) findViewById(R.id.send_button1);
        sendButton1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(Pop.this,
                    "ImageButton1 send!", Toast.LENGTH_SHORT).show();
        }

    });

    }


}
