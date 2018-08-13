package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.EazyPG.owner.DetailList.PreviousTenantDetailList;
import com.example.EazyPG.owner.DetailsClasses.TenantDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PreviousRoomTenantsActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    ListView listView;
    View emptyList;
    List<TenantDetails> tenantDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_room_tenants);

        Toolbar toolbar = findViewById(R.id.previousTenantToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = findViewById(R.id.listViewPreviousTenant);
        emptyList = findViewById(R.id.emptyListPreviousTenant);
        listView.setEmptyView(emptyList);

        tenantDetailsList = new ArrayList<>();

        Intent intent = getIntent();
        String roomNo = intent.getStringExtra(RoomTenantDetailsActivity.EXTRA_MESSAGE);


        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + roomNo + "/Tenant/PreviousTenants/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tenantDetailsList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    TenantDetails tenantDetails = snapshot.getValue(TenantDetails.class);
                    tenantDetailsList.add(tenantDetails);
                }

                PreviousTenantDetailList adapter = new PreviousTenantDetailList(PreviousRoomTenantsActivity.this, tenantDetailsList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
