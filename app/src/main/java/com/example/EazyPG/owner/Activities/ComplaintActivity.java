package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComplaintActivity extends AppCompatActivity {

    CardView bedroomComplaint, foodComplaint, facilityComplaint, securityComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        bedroomComplaint = findViewById(R.id.bedroomComplaint);
        foodComplaint = findViewById(R.id.foodComplaint);
        facilityComplaint = findViewById(R.id.facilityComplaint);
        securityComplaint = findViewById(R.id.securityComplaint);

        bedroomComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, BedroomComplaintActivity.class));
                finish();
            }
        });

        foodComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, FoodComplaintActivity.class));
                finish();
            }
        });

        facilityComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, FacilityComplaintActivity.class));
                finish();
            }
        });

        securityComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, SecurityComplaintActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ComplaintActivity.this, HomePageActivity.class));
        finish();
    }
}
