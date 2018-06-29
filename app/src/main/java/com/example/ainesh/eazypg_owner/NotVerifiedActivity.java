package com.example.ainesh.eazypg_owner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NotVerifiedActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    TextView statusTextView;

    Button sendEmailButton;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_verified);

        sendEmailButton = findViewById(R.id.sendMailButton);
        logoutButton = findViewById(R.id.logoutButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        statusTextView = findViewById(R.id.statusTextView);

        if (!firebaseUser.isEmailVerified()){

            statusTextView.setText("You are not Verified");
        }

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(NotVerifiedActivity.this, "Email Sent! Login again", Toast.LENGTH_SHORT).show();
                            firebaseAuth.getInstance().signOut();
                            startActivity(new Intent(NotVerifiedActivity.this, LoginActivity.class));
                            finish();

                        }
                    }
                });*/

                firebaseAuth.sendPasswordResetEmail(firebaseAuth.getCurrentUser().getEmail())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (!task.isSuccessful()){

                                    Toast.makeText(NotVerifiedActivity.this, "Email Not sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotVerifiedActivity.this, LoginActivity.class));
                firebaseAuth.getInstance().signOut();
            }
        });
    }
}
