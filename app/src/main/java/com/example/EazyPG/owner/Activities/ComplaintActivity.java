package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.EazyPG.owner.DetailList.ComplaintDetailList;
import com.example.EazyPG.owner.DetailsClasses.ComplaintDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ComplaintActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<ComplaintDetails> complaintDetailsList;

    CardView bedroomComplaint, foodComplaint, facilityComplaint, securityComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(33,33,33));
        }

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        complaintDetailsList = new ArrayList<>();

        bedroomComplaint = findViewById(R.id.bedroomComplaint);
        facilityComplaint = findViewById(R.id.facilityComplaint);
        foodComplaint = findViewById(R.id.foodComplaint);
        securityComplaint = findViewById(R.id.securityComplaint);

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaint/Bedroom/");

        DatabaseReference databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaints/Bedroom/");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ComplaintDetails complaintDetails1 = snapshot.getValue(ComplaintDetails.class);

                    if (complaintDetails1.status.equals("Unresolved"))
                    complaintDetailsList.add(complaintDetails1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference2 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaints/Mess & Food/");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ComplaintDetails complaintDetails1 = snapshot.getValue(ComplaintDetails.class);

                    if (complaintDetails1.status.equals("Unresolved"))
                    complaintDetailsList.add(complaintDetails1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference3 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaints/Facilities/");
        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ComplaintDetails complaintDetails1 = snapshot.getValue(ComplaintDetails.class);

                    if (complaintDetails1.status.equals("Unresolved"))
                    complaintDetailsList.add(complaintDetails1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference4 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaints/Management & Security/");
        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ComplaintDetails complaintDetails1 = snapshot.getValue(ComplaintDetails.class);

                    if (complaintDetails1.status.equals("Unresolved"))
                        complaintDetailsList.add(complaintDetails1);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bedroomComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, BedroomComplaintsFragmentActivity.class));
            }
        });

        foodComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, FoodComplaintsFragmentActivity.class));
            }
        });

        facilityComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, FacilityComplaintsFragmentActivity.class));
            }
        });

        securityComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, SecurityComplaintsFragmentActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ComplaintActivity.this, HomePageActivity.class));
        finish();
    }
}
