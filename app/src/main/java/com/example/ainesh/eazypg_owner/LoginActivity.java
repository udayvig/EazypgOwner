package com.example.ainesh.eazypg_owner;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
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

    private int RC_SIGN_IN=1;

    private EditText etUserEmail,etUserPassword;
    private ImageView btnSignIn;
    private TextView loginToSignIn;

    private String userEmail, userPassword;


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginToSignIn=(TextView) findViewById(R.id.tvLoginToSignUp);
        btnSignIn=(ImageView) findViewById(R.id.btnSignIn);
        etUserEmail=(EditText) findViewById(R.id.usernametextView);
        etUserPassword=(EditText) findViewById(R.id.passwordtextView);

        etUserEmail.requestFocus(1);
        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        //If user kills the app without logging out, he/she should need not to login again.
        if(mFirebaseUser!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail=etUserEmail.getText().toString().trim();
                userPassword=etUserPassword.getText().toString().trim();
                    if(!(userPassword.isEmpty()) && !(userEmail.isEmpty())) {
                        mFirebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
                                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Login Failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else {
                        if(userEmail.isEmpty()) {

                            etUserEmail.setError("Field cannot be empty");
                        }
                        if(userPassword.isEmpty()){

                            etUserPassword.setError("Field cannot be empty");

                        }
                    }

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
}
