package com.prototype.messageboard;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;

public class InfoActivity extends NavigationDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        // https://stackoverflow.com/questions/21405958/how-to-display-navigation-drawer-in-all-activities
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_info, null, false);
        mDrawerLayout.addView(contentView, 0);
    }

    @Override
    public void onPause(){
        super.onPause();
        mDrawerLayout.closeDrawers();
    }
}
