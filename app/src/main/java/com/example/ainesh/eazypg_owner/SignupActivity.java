package com.example.ainesh.eazypg_owner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mFirebaseAuth;

    private int RC_SIGN_IN=1;

    private EditText etUserEmail,etUserLocality,etUserContact,etUserName;
    private ImageView btnSignUp;


    private String userEmail, userLocality, userContact, userName;

    @Override
    public void onBackPressed() {
       startActivity(new Intent(SignupActivity.this,LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignUp=(ImageView) findViewById(R.id.btnSignUp);
        etUserEmail=(EditText) findViewById(R.id.emailEditText);       //Test
        etUserLocality=(EditText) findViewById(R.id.localityEditText); //Test
        etUserContact=(EditText) findViewById(R.id.contactEditText);       //Test
        etUserName=(EditText)findViewById(R.id.usernameEditText);      //Test

        mFirebaseAuth=FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userEmail=etUserEmail.getText().toString();
                userLocality=etUserLocality.getText().toString();
                userContact=etUserContact.getText().toString();
                userName=etUserName.getText().toString();

                if (!userEmail.isEmpty() && !userLocality.isEmpty() && !userContact.isEmpty() && !userName.isEmpty()) {

                    String password=passwordGeneration();
                    mFirebaseAuth.createUserWithEmailAndPassword(userEmail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
                else {
                    if (userName.isEmpty()){
                        etUserName.setError("Field cannot be empty!");
                    }

                    if(userEmail.isEmpty()){
                        etUserEmail.setError("Field cannot be empty!");
                    }
                    if (userLocality.isEmpty()){
                        etUserLocality.setError("Field cannot be empty!");
                    }
                    if (userContact.isEmpty()){
                        etUserContact.setError("Field cannot be empty!");
                    }
                }

            }
        });
    }

    public static String passwordGeneration(){
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*_=+-/.?<>)";


        String values = Capital_chars + Small_chars +
                numbers + symbols;

        // Using random method
        Random rndm_method = new Random();

        char[] password = new char[8];

        for (int i = 0; i < 8; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            password[i] =
                    values.charAt(rndm_method.nextInt(values.length()));

        }
        return password.toString();
    }
}
