package com.prototype.messageboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends NavigationDrawer implements View.OnClickListener{
    ImageButton imageButton1, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6;


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
        ImageButton one = (ImageButton) findViewById(R.id.imageButton1);
        one.setOnClickListener(HomeActivity.this); //call on click event
        ImageButton two = (ImageButton) findViewById(R.id.imageButton2);
        two.setOnClickListener(HomeActivity.this);
        ImageButton three = (ImageButton) findViewById(R.id.imageButton3);
        three.setOnClickListener(HomeActivity.this);
        ImageButton four = (ImageButton) findViewById(R.id.imageButton4);
        four.setOnClickListener(HomeActivity.this);
        ImageButton five = (ImageButton) findViewById(R.id.imageButton5);
        five.setOnClickListener(HomeActivity.this);
        ImageButton six = (ImageButton) findViewById(R.id.imageButton6);
        six.setOnClickListener(HomeActivity.this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HomeActivity.this, Pop.class);
        switch(v.getId()) {
            case R.id.imageButton1:
//              Blur image
//              Bitmap resultBmp = BlurBuilder.blur(HomeActivity.this, BitmapFactory.decodeResource(getResources(), R.drawable.baseline_android_black_18dp));
//              ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
//              imageButton1.setImageBitmap(resultBmp);
                intent.putExtra("imageButton", 1);
                startActivity(intent);
                break;
            case R.id.imageButton2:
                intent.putExtra("imageButton", 2);
                startActivity(intent);
                break;
            case R.id.imageButton3:
                intent.putExtra("imageButton", 3);
                startActivity(intent);
                break;
            case R.id.imageButton4:
                intent.putExtra("imageButton", 4);
                startActivity(intent);
                break;
            case R.id.imageButton5:
                intent.putExtra("imageButton", 5);
                startActivity(intent);
                break;
            case R.id.imageButton6:
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










