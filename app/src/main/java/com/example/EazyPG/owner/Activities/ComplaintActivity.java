package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.EazyPG.owner.DetailsClasses.ComplaintDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ComplaintActivity extends AppCompatActivity {

    CardView bedroomComplaint, foodComplaint, facilityComplaint, securityComplaint;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    int countBedroom, countFood, countFacility, countSecurity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        bedroomComplaint = findViewById(R.id.bedroomComplaint);
        foodComplaint = findViewById(R.id.foodComplaint);
        facilityComplaint = findViewById(R.id.facilityComplaint);
        securityComplaint = findViewById(R.id.securityComplaint);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        countBedroom = countFacility = countSecurity = countFood = 0;

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaints");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    ComplaintDetails complaintDetails = snapshot.child("Bedroom").getValue(ComplaintDetails.class);

                    if (!complaintDetails.resolved) {

                        countBedroom ++;
                    }

                }

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    ComplaintDetails complaintDetails = snapshot.child("Food").getValue(ComplaintDetails.class);

                    if (!complaintDetails.resolved) {

                        countFood ++;
                    }

                }

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    ComplaintDetails complaintDetails = snapshot.child("Facility").getValue(ComplaintDetails.class);

                    if (!complaintDetails.resolved) {

                        countFacility ++;
                    }

                }

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    ComplaintDetails complaintDetails = snapshot.child("Security").getValue(ComplaintDetails.class);

                    if (!complaintDetails.resolved) {

                        countSecurity ++;
                    }

                }

                // Add counts to bubbles


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
