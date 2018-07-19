package com.prototype.messageboard;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements FloatingActionMenu.MenuStateChangeListener{

    private de.hdodenhof.circleimageview.CircleImageView profile;
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
        /*if(DisplaySettingActivity.switch_on_tf == 1){
            setTheme(R.style.darktheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profile = findViewById(R.id.profile_image);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

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
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.display_settings:
                Intent intent = new Intent(HomeActivity.this,DisplaySettingActivity.class);
                startActivity(intent);
                return true;
            case R.id.custom_img:
                return true;
            default:
                return super.onOptionsItemSelected(item);

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