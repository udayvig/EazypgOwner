package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.EazyPG.owner.DetailList.ComplaintsDetailList;
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

public class BedroomComplaintActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView listView;
    List<ComplaintDetails> complaintDetailsList;
    View emptyList;

    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedroom_complaint);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        notificationManager = NotificationManagerCompat.from(this);

        Toolbar toolbar = findViewById(R.id.bedroomToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = findViewById(R.id.listViewBedroom);
        emptyList = findViewById(R.id.emptyListBedroom);
        listView.setEmptyView(emptyList);

        complaintDetailsList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("PG/" + firebaseUser.getUid() + "/Complaints");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                complaintDetailsList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    ComplaintDetails complaintDetails = snapshot.getValue(ComplaintDetails.class);

                    if (complaintDetails.firstLevel.equals("Bedroom")) {

                        complaintDetailsList.add(complaintDetails);
                    }
                }

                ComplaintsDetailList adapter = new ComplaintsDetailList(BedroomComplaintActivity.this, complaintDetailsList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BedroomComplaintActivity.this, HomePageActivity.class));
        finish();
    }
}
