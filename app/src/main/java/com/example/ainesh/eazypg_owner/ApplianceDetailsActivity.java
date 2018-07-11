package com.example.ainesh.eazypg_owner;

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
    ListView listViewAC;

    List<ApplianceDetailAC> acList;


    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_details);

        toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listViewAC = findViewById(R.id.listView);
        acList = new ArrayList<>();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();

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
                listViewAC.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
