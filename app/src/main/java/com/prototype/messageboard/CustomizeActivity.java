package com.prototype.messageboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;

public class CustomizeActivity extends NavigationDrawer implements FloatingActionMenu.MenuStateChangeListener, View.OnClickListener{

    private ArrayList<FloatingActionMenu> menus;
    private FloatingActionMenu currentMenu;
    int[] customizeIds = {R.id.customizeButton1,
                    R.id.customizeButton2,
                    R.id.customizeButton3,
                    R.id.customizeButton4,
                    R.id.customizeButton5,
                    R.id.customizeButton6};
    private CustomizeTxtPopup customizeTxtPopup;
    int pos;
    ImageView customBtn;
    static final int PICK_ICON_REQUEST = 1;
    private FirebaseStorage storage;

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
        customizeTxtPopup = new CustomizeTxtPopup(this);
        mDrawerLayout.addView(contentView, 0);

        storage = FirebaseStorage.getInstance();

        menus = new ArrayList<FloatingActionMenu>();

        for (int i=0; i<6; i++){
            ImageView img = findViewById(customizeIds[i]);
            TextView custom_txt = new TextView(this); custom_txt.setId(R.id.custom_txt); custom_txt.setText("Text"); custom_txt.setBackgroundResource(android.R.drawable.btn_default_small);
            TextView custom_img = new TextView(this); custom_img.setId(R.id.custom_img); custom_img.setText("Image"); custom_img.setBackgroundResource(android.R.drawable.btn_default_small);
            TextView custom_def = new TextView(this); custom_def.setId(R.id.custom_def); custom_def.setText("Default"); custom_def.setBackgroundResource(android.R.drawable.btn_default_small);
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

            custom_txt.setOnClickListener(this);
            custom_img.setOnClickListener(this);
            custom_def.setOnClickListener(this);

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
    public void onPause(){
        super.onPause();
        mDrawerLayout.closeDrawers();
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

    @Override
    public void onClick(View view) {
        pos = menus.indexOf(currentMenu);
        customBtn = findViewById(customizeIds[pos]);

        switch (view.getId()){
            case R.id.custom_txt:
                //customizeTxtPopup.PopupWindow(this, customBtn);
                break;

            case R.id.custom_img:
                Intent intent = new Intent(this, CustomizeIconActivity.class);
                startActivityForResult(intent,PICK_ICON_REQUEST);
                break;

            case R.id.custom_def:
                Toast.makeText(CustomizeActivity.this,
                        "Customize def clicked",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PICK_ICON_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                String iconPath = data.getStringExtra("iconPath");
                GlideApp.with(this).load(storage.getReference(iconPath)).into(customBtn);
            }
        }
    }
}
