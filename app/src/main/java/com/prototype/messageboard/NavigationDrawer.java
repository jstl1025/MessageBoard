package com.prototype.messageboard;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class NavigationDrawer extends AppCompatActivity {

    protected DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        //Add Drawer left swipe
        mDrawerLayout = (DrawerLayout) findViewById(R.id.homeLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        //Add Nav button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        int size = nv.getMenu().size();
                        for (int i = 0; i < size; i++) {
                            nv.getMenu().getItem(i).setChecked(false);
                        }
                        if (id == R.id.profile) {
                            menuItem.setChecked(true);
                            startActivity(new Intent(NavigationDrawer.this, ProfileActivity.class));
                            menuItem.setChecked(true);
                            //Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                            //mDrawerLayout.closeDrawers();
                        } else if (id == R.id.display_settings) {
                            menuItem.setChecked(true);
                            startActivity(new Intent(NavigationDrawer.this, DisplaySettingActivity.class));
                        } else if (id == R.id.custom) {
                            startActivity(new Intent(NavigationDrawer.this, CustomizeActivity.class));
                            menuItem.setChecked(true);

                        } else if (id == R.id.info) {
                            startActivity(new Intent(NavigationDrawer.this, InfoActivity.class));
                            menuItem.setChecked(true);
                        } else if (id == R.id.home) {
                            startActivity(new Intent(NavigationDrawer.this, HomeActivity.class));
                            menuItem.setChecked(true);
                        }

                        return true;

                    }

                }

        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }
}
