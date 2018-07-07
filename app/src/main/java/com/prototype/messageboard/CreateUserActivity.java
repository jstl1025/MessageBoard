package com.prototype.messageboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class CreateUserActivity extends AppCompatActivity {

    private Button signUp;
    private EditText userName;
    private EditText password;
    private EditText passwordHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        signUp=(Button) findViewById(R.id.signUp);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        passwordHint = (EditText) findViewById(R.id.passwordHint);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference userRef = database.getReference("User");

                String newUser = userName.getText().toString();
                String newPassword = password.getText().toString();
                String newHint = passwordHint.getText().toString();

                DatabaseReference userNameRef = userRef.child(newUser);
                userNameRef.setValue(newPassword);
            }
        });
    }
}
