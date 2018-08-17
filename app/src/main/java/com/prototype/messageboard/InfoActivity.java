package com.prototype.messageboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class InfoActivity extends NavigationDrawer {

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private ImageView emo1;

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

        /////
        emo1 = findViewById(R.id.emo1);
        Button upload = findViewById(R.id.testUpload);
        upload.setOnClickListener(new UploadOnClickListener());
    }

    @Override
    public void onPause(){
        super.onPause();
        mDrawerLayout.closeDrawers();
    }

    private class UploadOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.emo1);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
            byte[] data = baos.toByteArray();

            String path = "test/" + UUID.randomUUID() + ".png";
            StorageReference testRef = storage.getReference(path);

            UploadTask uploadTask = testRef.putBytes(data);
            uploadTask.addOnSuccessListener(InfoActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(InfoActivity.this, "Upload done", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
