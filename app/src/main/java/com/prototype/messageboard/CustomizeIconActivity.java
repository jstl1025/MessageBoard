package com.prototype.messageboard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class CustomizeIconActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseUser currentUser;
    private FirebaseStorage storage;
    private DatabaseReference ref;
    private ValueEventListener mIconsListener;
    private GridView gridView;
    private TextView cancel, confirm;
    private int selectedPos=-1;
    private boolean selected = false;
    private View selectedView;
    private ArrayList<StorageReference> storageRef;
    private ArrayList<String> iconWaiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_icon);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());
        storage = FirebaseStorage.getInstance();

        gridView = findViewById(R.id.gridView);
        cancel = findViewById(R.id.cancelSelect);
        confirm = findViewById(R.id.confirmSelect);

        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        ValueEventListener iconsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                iconWaiting = user.getIconWaiting();

                storageRef = new ArrayList<>();
                for(String pathStr : iconWaiting){
                    storageRef.add(storage.getReference(pathStr));
                }
                System.out.println("here "+storageRef);
                gridView.setAdapter(new IconAdapter(CustomizeIconActivity.this, storageRef));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addValueEventListener(iconsListener);
        mIconsListener = iconsListener;

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!selected){
                    view.setBackgroundColor(Color.parseColor("#000000"));
                    selected = true;
                    selectedPos = position;
                    selectedView = view;
                }
                else{
                    if(selectedPos == position){
                        view.setBackgroundColor(Color.TRANSPARENT);
                        selected = false;
                        selectedView = null;
                    }
                    else{
                        selectedView.setBackgroundColor(Color.TRANSPARENT);
                        view.setBackgroundColor(Color.BLACK);
                        selectedPos = position;
                        selectedView = view;
                    }
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mIconsListener !=null){
            ref.removeEventListener(mIconsListener);
        }
    }


    @Override
    public void onClick(View view){
        Intent returnIntent;
        switch(view.getId()){
            case R.id.cancelSelect:
                returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED,returnIntent);
                finish();
                break;

            case R.id.confirmSelect: //if no icon selected
                if(selected){
                    returnIntent = new Intent();
                    returnIntent.putExtra("iconWaiting", iconWaiting.get(selectedPos));
                    returnIntent.putExtra("iconWaitingPos",selectedPos);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
                else{
                    Toast.makeText(this,"No icon selected!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
