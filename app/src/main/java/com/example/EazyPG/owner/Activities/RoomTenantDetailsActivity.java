package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.EazyPG.owner.DetailList.RoomsDetailList;
import com.example.EazyPG.owner.DetailList.TenantDetailList;
import com.example.EazyPG.owner.DetailsClasses.TenantDetails;
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

public class RoomTenantDetailsActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_tenant_details);

        final List<TenantDetails> roomTenantDetailList;

        final ListView listViewRoomTenant;

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        roomTenantDetailList = new ArrayList<>();
        listViewRoomTenant = findViewById(R.id.listViewRoomTenant);

        Intent intent = getIntent();

        String roomNo = intent.getStringExtra(RoomsDetailList.EXTRA_MESSAGE);

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Rooms/" + roomNo + "/Tenant/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                roomTenantDetailList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    TenantDetails tenantDetails = snapshot.getValue(TenantDetails.class);
                    roomTenantDetailList.add(tenantDetails);
                }

                TenantDetailList adapter = new TenantDetailList(RoomTenantDetailsActivity.this, roomTenantDetailList);
                listViewRoomTenant.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
