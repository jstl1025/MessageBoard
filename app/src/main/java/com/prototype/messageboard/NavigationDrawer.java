package com.prototype.messageboard;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
    private Button logout_confirm, logout_cancel;
    private LinearLayout logout;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        userStateChange();

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
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            //Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                            //mDrawerLayout.closeDrawers();
                        } else if (id == R.id.display_settings) {
                            menuItem.setChecked(true);
                            startActivity(new Intent(NavigationDrawer.this, DisplaySettingActivity.class));
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        } else if (id == R.id.custom) {
                            startActivity(new Intent(NavigationDrawer.this, CustomizeActivity.class));
                            menuItem.setChecked(true);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        } else if (id == R.id.info) {
                            startActivity(new Intent(NavigationDrawer.this, InfoActivity.class));
                            menuItem.setChecked(true);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        } else if (id == R.id.home) {
                            startActivity(new Intent(NavigationDrawer.this, HomeActivity.class));
                            menuItem.setChecked(true);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }

                        return true;

                    }

                }

        );

        //Sign out user
        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logoutView);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutPopup(NavigationDrawer.this);
            }
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        fetchUsername(currentUser);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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

    public void userStateChange(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(NavigationDrawer.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    private void logoutPopup(Context context){

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.logout_popup, null);

        float density=NavigationDrawer.this.getResources().getDisplayMetrics().density;

        final PopupWindow logoutPopup = new PopupWindow(layout, (int)density*240, (int)density*285, true);
        logoutPopup.setContentView(layout);
        logoutPopup.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        logoutPopup.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        logoutPopup.setFocusable(true);
        logoutPopup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        logout_confirm = layout.findViewById(R.id.logout_confirm);
        logout_cancel = layout.findViewById(R.id.logout_cancel);
        logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                userStateChange();
                logoutPopup.dismiss();
            }
        });
        logout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutPopup.dismiss();
            }
        });
    }
}
