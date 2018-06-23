package com.example.ainesh.eazypg_owner;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button btnSignIn;
    private TextView loginToSignIn;

    private String userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginToSignIn=(TextView) findViewById(R.id.tvLoginToSignUp);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);
        etUserEmail=(EditText) findViewById(R.id.usernametextView);
        etUserPassword=(EditText) findViewById(R.id.passwordtextView);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        //If user kills the app without logging out, he/she should need not to login again.
        if(mFirebaseUser!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,DummyActivity.class));
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
                            startActivity(new Intent(LoginActivity.this,DummyActivity.class));
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
            }
        });
    }
}
