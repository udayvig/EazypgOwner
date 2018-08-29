package com.example.EazyPG.owner.Activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.EazyPG.owner.DetailList.HostFriendDetailList;
import com.example.EazyPG.owner.DetailsClasses.GuestDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HostFriendLogActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<GuestDetails> guestDetailsList;

    HostFriendDetailList hostFriendDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_friend_log);

        recyclerView = findViewById(R.id.hostFriendLogRecyclerView);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();

        guestDetailsList = new ArrayList<>();

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Host Friend/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                guestDetailsList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    GuestDetails guestDetails = snapshot.getValue(GuestDetails.class);
                    guestDetailsList.add(guestDetails);
                }

                hostFriendDetailList = new HostFriendDetailList(guestDetailsList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(hostFriendDetailList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
