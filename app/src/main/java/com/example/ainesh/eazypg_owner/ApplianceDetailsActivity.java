package com.example.ainesh.eazypg_owner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ainesh.eazypg_owner.Adapter.MyAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ApplianceDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar ;
    ListView listView;

    List<ApplianceDetailAC> acList;
    List<ApplianceDetailFan> fanList;


    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    private Intent intent;
    private String message;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_details);

        toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = findViewById(R.id.listView);

        acList = new ArrayList<>();
        fanList = new ArrayList<>();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();

        intent = getIntent();
        message = intent.getStringExtra(ApplianceAdapter.EXTRA_MESSAGE);

        if (message.equals("AC")) {
            databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/AC");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    acList.clear();

                    for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                        ApplianceDetailAC applianceDetailAC = dataSnapshotAC.getValue(ApplianceDetailAC.class);
                        acList.add(applianceDetailAC);
                    }

                    ACDetailList adapter = new ACDetailList(ApplianceDetailsActivity.this, acList);
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else if (message.equals("Fan")) {

            databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Fan");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    fanList.clear();

                    for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                        ApplianceDetailFan applianceDetailFan = dataSnapshotAC.getValue(ApplianceDetailFan.class);
                        fanList.add(applianceDetailFan);
                    }

                    FanDetailList adapter = new FanDetailList(ApplianceDetailsActivity.this, fanList);
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

}
