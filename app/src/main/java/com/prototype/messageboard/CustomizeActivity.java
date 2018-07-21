package com.prototype.messageboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;

public class CustomizeActivity extends NavigationDrawer implements FloatingActionMenu.MenuStateChangeListener{

    private ArrayList<FloatingActionMenu> menus;
    private FloatingActionMenu currentMenu;
    int[] imgIds = {R.id.imageButton1,
                    R.id.imageButton2,
                    R.id.imageButton3,
                    R.id.imageButton4,
                    R.id.imageButton5,
                    R.id.imageButton6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_customize);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_customize, null, false);
        mDrawerLayout.addView(contentView, 0);

        menus = new ArrayList<FloatingActionMenu>();

        for (int i=0; i<6; i++){
            ImageButton img = findViewById(imgIds[i]);
            TextView custom_txt = new TextView(this); custom_txt.setText("Text"); custom_txt.setBackgroundResource(android.R.drawable.btn_default_small);
            TextView custom_img = new TextView(this); custom_img.setText("Image"); custom_img.setBackgroundResource(android.R.drawable.btn_default_small);
            TextView custom_def = new TextView(this); custom_def.setText("Default"); custom_def.setBackgroundResource(android.R.drawable.btn_default_small);
            custom_txt.setBackgroundResource(R.drawable.rounded_textview);
            custom_txt.setGravity(Gravity.CENTER);
            custom_img.setBackgroundResource(R.drawable.rounded_textview);
            custom_img.setGravity(Gravity.CENTER);
            custom_def.setBackgroundResource(R.drawable.rounded_textview);
            custom_def.setGravity(Gravity.CENTER);

            FrameLayout.LayoutParams tvParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            custom_txt.setLayoutParams(tvParams);
            custom_img.setLayoutParams(tvParams);
            custom_def.setLayoutParams(tvParams);
            SubActionButton.Builder subBuilder = new SubActionButton.Builder(this);

            FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                    .setStartAngle(-50)
                    .setEndAngle(50)
                    .setRadius(getResources().getDimensionPixelSize(R.dimen.radius_large))
                    .addSubActionView(custom_txt)
                    .addSubActionView(custom_img)
                    .addSubActionView(custom_def)
                    .setStateChangeListener(this)
                    .attachTo(img)
                    .build();

            menus.add(actionMenu);
        }

    }

    @Override
    public void onMenuOpened(FloatingActionMenu menu){
        // Only allow one menu to stay open
        for(FloatingActionMenu iMenu : menus){
            iMenu.close(true);
        }
        // update current menu reference
        currentMenu=menu;
    }

    @Override
    public void onMenuClosed(FloatingActionMenu menu) {
        // remove current menu reference
        currentMenu = null;
    }

}