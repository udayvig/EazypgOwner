package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

public class FacilityComplaintActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<ComplaintDetails> complaintDetailsList;

    RecyclerView recyclerView;
    ComplaintDetailList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_complaint);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        Toolbar toolbar = findViewById(R.id.facilityToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        complaintDetailsList = new ArrayList<>();
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        DatabaseReference databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaints/Facilities/");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                complaintDetailsList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ComplaintDetails complaintDetails1 = snapshot.getValue(ComplaintDetails.class);

                    if (complaintDetails1.status.equals("Unresolved"))
                        complaintDetailsList.add(complaintDetails1);

                }

                adapter = new ComplaintDetailList(complaintDetailsList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FacilityComplaintActivity.this, HomePageActivity.class));
        finish();
    }
}
