package com.example.ainesh.eazypg_owner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private int RC_SIGN_IN = 1;

    private EditText etUserEmail,etUserPassword;
    private ImageView btnSignIn;
    private TextView loginToSignIn;

    private String userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginToSignIn = findViewById(R.id.tvLoginToSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        etUserEmail = findViewById(R.id.usernametextView);
        etUserPassword = findViewById(R.id.passwordtextView);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        //If user kills the app without logging out, he/she should need not to login again.
        if(mFirebaseUser!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,NotVerifiedActivity.class));
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail=etUserEmail.getText().toString().trim();
                userPassword=etUserPassword.getText().toString().trim();

                mFirebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            mFirebaseUser = mFirebaseAuth.getCurrentUser();

                            if (mFirebaseUser.isEmailVerified()){
                                startActivity(new Intent(LoginActivity.this, MyPGActivity.class));
                                finish();
                            }
                            else {
                                startActivity(new Intent(LoginActivity.this, NotVerifiedActivity.class));
                                finish();
                            }

                            Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "Login Failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        loginToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
