package com.prototype.messageboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;

public class CustomizeActivity extends NavigationDrawer implements FloatingActionMenu.MenuStateChangeListener, View.OnClickListener{

    private ArrayList<FloatingActionMenu> menus;
    private FloatingActionMenu currentMenu;
    private int[] customizeIds = {R.id.customizeButton1,
                    R.id.customizeButton2,
                    R.id.customizeButton3,
                    R.id.customizeButton4,
                    R.id.customizeButton5,
                    R.id.customizeButton6};
    private ArrayList<ImageView> imgView;
    private CustomizeTxtPopup customizeTxtPopup;
    int pos;
    private ImageView customBtn;
    static final int PICK_ICON_REQUEST = 1;
    private FirebaseStorage storage;
    private DatabaseReference ref;
    private ValueEventListener mIconListener;
    private User user;

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

        ref = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        storage = FirebaseStorage.getInstance();

        menus = new ArrayList<>();
        imgView = new ArrayList<>();

        for (int i=0; i<6; i++){
            ImageView img = findViewById(customizeIds[i]);
            imgView.add(img);
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
    protected void onStart() {
        super.onStart();

        ValueEventListener iconListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                ArrayList<String> iconInUse = user.getIconInUse();

                for(int i = 0; i<iconInUse.size(); i++){
                    GlideApp.with(CustomizeActivity.this)
                            .load(storage.getReference(iconInUse.get(i)))
                            .into(imgView.get(i));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addValueEventListener(iconListener);

        //keep a copy of iconListener to remove when app stops
        mIconListener = iconListener;
    }

    @Override
    protected void onStop() {
        super.onStop();
        //remove icon listener
        if(mIconListener!=null){
            ref.removeEventListener(mIconListener);
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
                /*String iconWaiting = data.getStringExtra("iconWaiting");*/
                int iconWaitingPos = data.getIntExtra("iconWaitingPos",-1);
                swapIconXArray(pos,iconWaitingPos);
                /*GlideApp.with(this).load(storage.getReference(iconWaiting)).into(customBtn);*/
            }
        }
    }

    private void swapIconXArray(int pos, int iconWaitingPos) {
        ArrayList<String> iconInUse = user.getIconInUse();
        ArrayList<String> iconWaiting = user.getIconWaiting();

        if (pos < iconInUse.size()) {       //Currently set to swap only 3 icons
            String temp = iconInUse.get(pos);
            iconInUse.set(pos, iconWaiting.get(iconWaitingPos));
            iconWaiting.set(iconWaitingPos, temp);
            user.setIconInUse(iconInUse);
            user.setIconWaiting(iconWaiting);
/*        System.out.println("sss"+iconInUse);
        System.out.println("sss"+iconWaiting);*/
            ref.child("iconInUse").setValue(iconInUse);
            ref.child("iconWaiting").setValue(iconWaiting);
        }
    }
}
