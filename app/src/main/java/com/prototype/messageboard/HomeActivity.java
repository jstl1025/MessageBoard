package com.prototype.messageboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class HomeActivity extends NavigationDrawer implements View.OnClickListener{
    Button /*homeBtn1,*/ homeBtn2, homeBtn3, homeBtn4, homeBtn5, homeBtn6;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    ImageView homeBtn1;


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



        //addListenerOnButton();
        homeBtn1 = findViewById(R.id.homeButton1);
        homeBtn1.setOnClickListener(HomeActivity.this); //call on click event
        homeBtn2 = findViewById(R.id.homeButton2);
        homeBtn2.setOnClickListener(HomeActivity.this);
        homeBtn3 = findViewById(R.id.homeButton3);
        homeBtn3.setOnClickListener(HomeActivity.this);
        homeBtn4 = findViewById(R.id.homeButton4);
        homeBtn4.setOnClickListener(HomeActivity.this);
        homeBtn5 = findViewById(R.id.homeButton5);
        homeBtn5.setOnClickListener(HomeActivity.this);
        homeBtn6 = findViewById(R.id.homeButton6);
        homeBtn6.setOnClickListener(HomeActivity.this);

        refreshBtn();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HomeActivity.this, Pop.class);
        switch(v.getId()) {
            case R.id.homeButton1:
//              Blur image
//              Bitmap resultBmp = BlurBuilder.blur(HomeActivity.this, BitmapFactory.decodeResource(getResources(), R.drawable.baseline_android_black_18dp));
//              ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
//              imageButton1.setImageBitmap(resultBmp);
                intent.putExtra("imageButton", 1);
                startActivity(intent);
                break;
            case R.id.homeButton2:
                intent.putExtra("imageButton", 2);
                startActivity(intent);
                break;
            case R.id.homeButton3:
                intent.putExtra("imageButton", 3);
                startActivity(intent);
                break;
            case R.id.homeButton4:
                intent.putExtra("imageButton", 4);
                startActivity(intent);
                break;
            case R.id.homeButton5:
                intent.putExtra("imageButton", 5);
                startActivity(intent);
                break;
            case R.id.homeButton6:
                intent.putExtra("imageButton", 6);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void refreshBtn(){
        String path = "dIcons/" + "emo1" + ".png"; //middle string replace with user custom array later
        StorageReference storageRef = storage.getReference(path);

        GlideApp.with(this)
                .load(storageRef)
                .into(homeBtn1);
    }


//    public void addListenerOnButton() {
//        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
//        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);

//        imageButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,
//                        "ImageButton is clicked!", Toast.LENGTH_SHORT).show();
//                //Blur image
////                Bitmap resultBmp = BlurBuilder.blur(HomeActivity.this, BitmapFactory.decodeResource(getResources(), R.drawable.baseline_android_black_18dp));
////                ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
////                imageButton1.setImageBitmap(resultBmp);
//
//                startActivity(new Intent(HomeActivity.this, Pop.class));
//            }
//        });








}










