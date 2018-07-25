package com.prototype.messageboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends NavigationDrawer{
    ImageButton imageButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);

        // https://stackoverflow.com/questions/21405958/how-to-display-navigation-drawer-in-all-activities
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //attachtoRoot = true: layout file is inflated and attach to ViewGroup
        //attachtoRoot = false: layout file is inflated and returned as View
        View contentView = inflater.inflate(R.layout.activity_home, null, false);
        mDrawerLayout.addView(contentView, 0);

        addListenerOnButton();

    }


    public void addListenerOnButton() {
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this,
                        "ImageButton is clicked!", Toast.LENGTH_SHORT).show();

                //Blur image
//                Bitmap resultBmp = BlurBuilder.blur(HomeActivity.this, BitmapFactory.decodeResource(getResources(), R.drawable.baseline_android_black_18dp));
//                ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
//                imageButton1.setImageBitmap(resultBmp);

                startActivity(new Intent(HomeActivity.this, Pop.class));
            }

        });






    }

}










