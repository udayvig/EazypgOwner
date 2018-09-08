package com.example.EazyPG.owner.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.EazyPG.owner.DetailsClasses.BillDetails;
import com.example.EazyPG.owner.DetailsClasses.TenantDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillCollectionActivity extends AppCompatActivity {

    RecyclerView electricityRecyclerView, wifiRecyclerView, gasRecyclerView, otherRecyclerView;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    List<TenantDetails> tenantList = new ArrayList<>();
    List<BillDetails> electricityBillList = new ArrayList<>();
    List<BillDetails> wifiBillList = new ArrayList<>();
    List<BillDetails> gasBillList = new ArrayList<>();
    List<BillDetails> otherBillList = new ArrayList<>();
    List<BillDetails> billList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_collection);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        electricityRecyclerView = findViewById(R.id.electricityBillRecyclerView);
        wifiRecyclerView = findViewById(R.id.wifiBillRecyclerView);
        gasRecyclerView = findViewById(R.id.gasBillRecyclerView);
        otherRecyclerView = findViewById(R.id.otherBillRecyclerView);

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Tenants/CurrentTenants/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    TenantDetails tenantDetails = snapshot.getValue(TenantDetails.class);
                    tenantList.add(tenantDetails);
                }

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String dateStr = dateFormat.format(date);

                final String dateString = dateStr.substring(6,10) + "-" + dateStr.substring(3,5);

                final DatabaseReference databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Tenants/CurrentTenants/");
                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(int i = 0; i < tenantList.size(); i++){
                            dataSnapshot.child(tenantList.get(i).id).child("Accounts").child("Bills").child(dateString);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
