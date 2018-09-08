package com.example.EazyPG.owner.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class AddBillRoomActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<String> roomTypeList = new ArrayList<>();
    List<String> roomsList = new ArrayList<>();

    RecyclerView addBillRoomRecyclerView;

    AddBillRoomElectricityDetailList addBillRoomElectricityDetailList;
    AddBillRoomWifiDetailList addBillRoomWifiDetailList;
    AddBillRoomGasDetailList addBillRoomGasDetailList;
    AddBillRoomOtherDetailList addBillRoomOtherDetailList;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill_room);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        addBillRoomRecyclerView = findViewById(R.id.addBillRoomRecyclerView);

        context = getApplicationContext();

        Intent intent = getIntent();
        final String billType = intent.getStringExtra(AddBillActivity.EXTRA_MESSAGE);

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Rooms/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                roomsList.clear();
                roomTypeList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    roomsList.add(snapshot.getKey());
                    String roomType = snapshot.child("Room Type").getValue(String.class);
                    roomTypeList.add(roomType);
                }

                if(billType.equals("Electricity")) {
                    DatabaseReference databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/PG Details/");
                    databaseReference1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String unitCost = dataSnapshot.child("Unit Cost").getValue(String.class);

                            addBillRoomElectricityDetailList = new AddBillRoomElectricityDetailList(context, roomsList, roomTypeList, unitCost);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            addBillRoomRecyclerView.setLayoutManager(layoutManager);
                            addBillRoomRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            addBillRoomRecyclerView.setAdapter(addBillRoomElectricityDetailList);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                if(billType.equals("Wifi")) {
                    addBillRoomWifiDetailList = new AddBillRoomWifiDetailList(context, roomsList, roomTypeList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    addBillRoomRecyclerView.setLayoutManager(layoutManager);
                    addBillRoomRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    addBillRoomRecyclerView.setAdapter(addBillRoomWifiDetailList);
                }

                if(billType.equals("Gas")) {
                    addBillRoomGasDetailList = new AddBillRoomGasDetailList(context, roomsList, roomTypeList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    addBillRoomRecyclerView.setLayoutManager(layoutManager);
                    addBillRoomRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    addBillRoomRecyclerView.setAdapter(addBillRoomGasDetailList);
                }

                if(billType.equals("Other")) {
                    addBillRoomOtherDetailList = new AddBillRoomOtherDetailList(context, roomsList, roomTypeList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    addBillRoomRecyclerView.setLayoutManager(layoutManager);
                    addBillRoomRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    addBillRoomRecyclerView.setAdapter(addBillRoomOtherDetailList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
