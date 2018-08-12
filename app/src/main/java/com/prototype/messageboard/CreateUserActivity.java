package com.prototype.messageboard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUserActivity extends LoadingDialog {

    private static final String TAG = "EmailPassword";

    private Button signUpButton;
    private EditText userNameField;
    private EditText passwordField;
    private EditText passwordHintField;
    private EditText emailField;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String userName, password, hint, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //button & views
        signUpButton = findViewById(R.id.signUp);
        userNameField = findViewById(R.id.userName);
        passwordField =  findViewById(R.id.password);
        passwordHintField = findViewById(R.id.passwordHint);
        emailField = findViewById(R.id.email);

        //real time database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //initialize auth
        mAuth = FirebaseAuth.getInstance();

        //hide keyboard when click outside of EditText
        findViewById(R.id.signUpPage).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                hideKeyboard(getCurrentFocus());
                return true;
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = userNameField.getText().toString();
                password = passwordField.getText().toString();
                hint = passwordHintField.getText().toString();
                email = emailField.getText().toString();
                createAccount(userName,password,hint,email);
            }
        });
    }

    private void createAccount(String userName, String password, String passwordHint, String email){
        Log.d(TAG, "createAccount:"+email);
        if (!validateForm()){
            return;
        }

        hideKeyboard(this.getCurrentFocus());
        showProgressDialog(R.string.signup);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "createUserWithEmail:success");
                    createNewUser(task.getResult().getUser());
                    startActivity(new Intent(CreateUserActivity.this,HomeActivity.class));
                    finish();
                }else{
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(CreateUserActivity.this, "Email is already in use",Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
                hideProgressDialog();
            }
        });
        //writeNewUser(userName,password,passwordHint);
    }

    private boolean validateForm(){
        boolean valid = true;

        if (TextUtils.isEmpty(userName)){
            userNameField.setError("REQUIRED");
            valid = false;
        }else{
            userNameField.setError(null);
        }

        if (TextUtils.isEmpty(password) || password.length() < 6){
            passwordField.setError("REQUIRED, enter minimum 6 characters!");
            valid = false;
        }else{
            passwordField.setError(null);
        }

        if (!isEmailValid(email)){
            emailField.setError("Not a valid email format");
            valid = false;
        }else{
            emailField.setError(null);
        }
        return valid;
    }

    private void createNewUser(FirebaseUser newUser){
        String userId = newUser.getUid();

        User user = new User(userName, newUser.getEmail(), hint);

        mDatabase.child("users").child(userId).setValue(user);

    }

    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return boolean true for valid false for invalid
     */
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
