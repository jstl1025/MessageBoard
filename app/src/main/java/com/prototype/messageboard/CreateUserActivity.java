package com.prototype.messageboard;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class CreateUserActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private Button signUpButton;
    private EditText userNameField;
    private EditText passwordField;
    private EditText passwordHintField;
    private EditText emailField;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //button & views
        signUpButton=(Button) findViewById(R.id.signUp);
        userNameField = (EditText) findViewById(R.id.userName);
        passwordField = (EditText) findViewById(R.id.password);
        passwordHintField = (EditText) findViewById(R.id.passwordHint);
        emailField = (EditText) findViewById(R.id.email);

        //real time database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //initialize auth
        mAuth = FirebaseAuth.getInstance();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(
                        userNameField.getText().toString(),
                        passwordField.getText().toString(),
                        passwordHintField.getText().toString(),
                        emailField.getText().toString()
                );
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    private void createAccount(String userName, String password, String passwordHint, String email){
        Log.d(TAG, "createAccount:"+email);
        if (!validateForm()){
            return;
        }

        //showProgressDialog();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    //updateUI(user);
                }else{
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(CreateUserActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
                //hideProgressDialog();
            }
        });
        //writeNewUser(userName,password,passwordHint);
    }

    private boolean validateForm(){
        boolean valid = true;

        String userName = userNameField.getText().toString();
        String password = passwordField.getText().toString();
        String email = emailField.getText().toString();


        if (TextUtils.isEmpty(userName)){
            userNameField.setError("REQUIRED");
            valid = false;
        }else{
            userNameField.setError(null);
        }

        if (TextUtils.isEmpty(password)){
            passwordField.setError("REQUIRED");
            valid = false;
        }else{
            passwordField.setError(null);
        }

        if (TextUtils.isEmpty(email)){
            emailField.setError("REQUIRED");
            valid = false;
        }else{
            emailField.setError(null);
        }
        return valid;
    }

    /*private void updateUI(FirebaseUser user){
        //hideProgrssDialog();

        if (user!=null){

        }
    }*/

    /*private void writeNewUser(String userName, String password, String passwordHint){
        User user = new User(userName);
        user.setPassword(password);
        user.setPasswordHint(passwordHint);

        mDatabase.child("users").child(userName).setValue(user);
    }*/
}
