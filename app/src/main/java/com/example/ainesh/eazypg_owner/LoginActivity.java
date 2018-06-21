package com.example.ainesh.eazypg_owner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private int RC_SIGN_IN=1;

    private EditText etUserEmail,etUserPassword;
    private Button btnSignIn;

    private String userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn=(Button) findViewById(R.id.btnSignIn);
        etUserEmail=(EditText) findViewById(R.id.usernametextView);
        etUserPassword=(EditText) findViewById(R.id.passwordtextView);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        //If user kills the app without logging out, he/she should need not to login again.
        /*if(mFirebaseUser!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }*/

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail=etUserEmail.getText().toString().trim();
                userPassword=etUserPassword.getText().toString().trim();
                if(userEmail.isEmpty()||userPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Enter all the details!", Toast.LENGTH_SHORT).show();
                }



                
            }
        });
    }
}
