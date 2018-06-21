package com.example.ainesh.eazypg_owner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private int RC_SIGN_IN=1;

    private EditText etUserEmail,etUserPassword,etUserContact,etUsername;
    private Button btnSignUp;

    private String userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignUp=(Button) findViewById(R.id.btnSignUp);
        etUserEmail=(EditText) findViewById(R.id.emailtextView);       //Test
        etUserPassword=(EditText) findViewById(R.id.localitytextView); //Test
        etUserContact=(EditText) findViewById(R.id.contactView);       //Test
        etUsername=(EditText)findViewById(R.id.usernametextView);      //Test

        mFirebaseAuth=FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail=etUserEmail.getText().toString();
                userPassword=etUserPassword.getText().toString();
                if(userEmail.isEmpty()||userPassword.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please enter all details!", Toast.LENGTH_SHORT).show();
                }else{
                    mFirebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignupActivity.this, "Registered!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this,DummyActivity.class));
                            }else{
                                Toast.makeText(SignupActivity.this, "Failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
