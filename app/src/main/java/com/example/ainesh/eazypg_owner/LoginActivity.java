package com.example.ainesh.eazypg_owner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView loginToSignIn,forgotPassword;

    private String userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText input=new EditText(this);

        loginToSignIn = findViewById(R.id.tvLoginToSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        etUserEmail = findViewById(R.id.usernametextView);
        etUserPassword = findViewById(R.id.passwordtextView);
        forgotPassword=findViewById(R.id.forgotPasswordTextView);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        //If user kills the app without logging out, he/she should not need to login again.
        if(mFirebaseUser!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail=etUserEmail.getText().toString().trim();
                userPassword=etUserPassword.getText().toString().trim();

                if(!userEmail.isEmpty()&&!userPassword.isEmpty()) {
                    mFirebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                mFirebaseUser = mFirebaseAuth.getCurrentUser();
                                startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
                                finish();
                                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login Failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    if(userEmail.isEmpty()){
                        etUserEmail.setError("Field cannot be empty.");
                    }
                    if(userPassword.isEmpty()){
                        etUserPassword.setError("Field cannot be empty.");
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

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getParent()!=null) {
                    ((ViewGroup) input.getParent()).removeView(input);
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Reset Password")
                        .setMessage("Enter your Email Id")
                        .setIcon(R.drawable.ic_email_black_24dp)
                        .setView(input)
                        .setPositiveButton("Send Email", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mFirebaseAuth.sendPasswordResetEmail(input.getText().toString().trim())
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(LoginActivity.this, "Mail Sent!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
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
