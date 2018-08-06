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

public class LoginActivity extends LoadingDialog {
    private static final String TAG = "EmailPassword";
    private EditText email, password;
    private Button signInBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }

        //hide keyboard when click outside of EditText
        findViewById(R.id.loginPage).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                hideKeyboard(getCurrentFocus());
                return true;
            }
        });

        email = findViewById(R.id.lEmail);
        password = findViewById(R.id.lPassword);
        signInBtn = findViewById(R.id.signIn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(email.getText().toString(),password.getText().toString());
            }
        });
    }

    public void createUser(View view){
        startActivity(new Intent(this, CreateUserActivity.class));
    }

    private void signIn(String email, String password){
        Log.d(TAG,"signIn:"+email);

        if(!validateForm()){
            return;
        }
        hideKeyboard(this.getCurrentFocus());
        showProgressDialog(R.string.loading);

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "signInWithEmail:success");
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        }else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                        hideProgressDialog();
                    }
                });
    }

    private boolean validateForm(){
        boolean valid = true;

        String Email = email.getText().toString();
        String Password = password.getText().toString();

        if (TextUtils.isEmpty(Email)){
            email.setError("REQUIRED");
            valid = false;
        }else{
            email.setError(null);
        }

        if (TextUtils.isEmpty(Password)){
            password.setError("REQUIRED");
            valid = false;
        }else{
            password.setError(null);
        }

        return valid;
    }




}
