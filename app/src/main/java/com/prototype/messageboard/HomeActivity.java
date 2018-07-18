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

public class HomeActivity extends AppCompatActivity {

    private de.hdodenhof.circleimageview.CircleImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(DisplaySettingActivity.switch_on_tf == 1){
            setTheme(R.style.darktheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
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

        ImageButton img1 = (ImageButton) findViewById(R.id.imageButton1);


        TextView custom_txt = new TextView(this); custom_txt.setText("Text"); custom_txt.setBackgroundResource(android.R.drawable.btn_default_small);
        TextView custom_img = new TextView(this); custom_img.setText("Image"); custom_img.setBackgroundResource(android.R.drawable.btn_default_small);
        TextView custom_def = new TextView(this); custom_def.setText("Default"); custom_def.setBackgroundResource(android.R.drawable.btn_default_small);
        custom_txt.setBackgroundResource(R.drawable.rounded_textview);
        custom_txt.setGravity(Gravity.CENTER);
        custom_img.setBackgroundResource(R.drawable.rounded_textview);
        custom_img.setGravity(Gravity.CENTER);
        custom_def.setBackgroundResource(R.drawable.rounded_textview);
        custom_def.setGravity(Gravity.CENTER);

        ImageButton img2 = (ImageButton) findViewById(R.id.imageButton2);
        TextView custom_txt2 = new TextView(this); custom_txt2.setText("Text"); custom_txt2.setBackgroundResource(android.R.drawable.btn_default_small);
        TextView custom_img2 = new TextView(this); custom_img2.setText("Image"); custom_img2.setBackgroundResource(android.R.drawable.btn_default_small);
        TextView custom_def2 = new TextView(this); custom_def2.setText("Default"); custom_def2.setBackgroundResource(android.R.drawable.btn_default_small);
        custom_txt2.setBackgroundResource(R.drawable.rounded_textview);
        custom_txt2.setGravity(Gravity.CENTER);
        custom_img2.setBackgroundResource(R.drawable.rounded_textview);
        custom_img2.setGravity(Gravity.CENTER);
        custom_def2.setBackgroundResource(R.drawable.rounded_textview);
        custom_def2.setGravity(Gravity.CENTER);

        FrameLayout.LayoutParams tvParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        custom_txt.setLayoutParams(tvParams);
        custom_img.setLayoutParams(tvParams);
        custom_def.setLayoutParams(tvParams);

        custom_txt2.setLayoutParams(tvParams);
        custom_img2.setLayoutParams(tvParams);
        custom_def2.setLayoutParams(tvParams);
        SubActionButton.Builder subBuilder = new SubActionButton.Builder(this);


        FloatingActionMenu actionMenu1 = new FloatingActionMenu.Builder(this)
                .setStartAngle(-50)
                .setEndAngle(50)
                .setRadius(getResources().getDimensionPixelSize(R.dimen.radius_large))
                .addSubActionView(custom_txt)
                .addSubActionView(custom_img)
                .addSubActionView(custom_def)
                .attachTo(img1)
                .build();

        FloatingActionMenu actionMenu2 = new FloatingActionMenu.Builder(this)
                .setStartAngle(-50)
                .setEndAngle(50)
                .setRadius(getResources().getDimensionPixelSize(R.dimen.radius_large))
                .addSubActionView(custom_txt2)
                .addSubActionView(custom_img2)
                .addSubActionView(custom_def2)
                .attachTo(img2)
                .build();

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
}
