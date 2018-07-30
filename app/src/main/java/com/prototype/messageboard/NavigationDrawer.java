package com.prototype.messageboard;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NavigationDrawer extends AppCompatActivity {

    protected DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView nv;
    private Toolbar mToolbar;
    private View headerView;
    private TextView nv_username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        //overlay toolbar
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

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

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        fetchUsername(currentUser);
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

    private void fetchUsername(FirebaseUser currentUser){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users/"+currentUser.getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                headerView = nv.getHeaderView(0);
                nv_username = headerView.findViewById(R.id.nv_username);
                nv_username.setText(user.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
