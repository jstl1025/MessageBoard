package com.prototype.messageboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends NavigationDrawer implements View.OnClickListener{
    private Button delete;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_profile, null, false);
        mDrawerLayout.addView(contentView, 0);

        progressBar = findViewById(R.id.simpleProgressBar);

        delete=findViewById(R.id.deleteAccount);
        delete.setOnClickListener(this);


    }

    @Override
    public void onPause(){
        super.onPause();
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onClick(View view) {
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        switch (view.getId()){
            case R.id.deleteAccount:
                progressBar.setVisibility(View.VISIBLE);
                currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users/"+currentUser.getUid());
                            ref.removeValue();
                            userStateChange();
                        }else{
                            Toast.makeText(ProfileActivity.this,"Error: unable to delete",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

        }
    }
}
