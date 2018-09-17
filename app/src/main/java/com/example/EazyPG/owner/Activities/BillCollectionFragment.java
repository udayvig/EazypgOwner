package com.example.EazyPG.owner.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class BillCollectionFragment extends Fragment {
    View view;

    RecyclerView billRecyclerView;

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

    BillCollectionDetailList billCollectionDetailList;

    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_bill_collection, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        billRecyclerView = view.findViewById(R.id.billRecyclerView);

        context = getContext();

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Tenants/CurrentTenants/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TenantDetails tenantDetails = snapshot.getValue(TenantDetails.class);
                    tenantList.add(tenantDetails);
                }

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String dateStr = dateFormat.format(date);

                final String dateString = dateStr.substring(6, 10) + "-" + dateStr.substring(3, 5);

                final DatabaseReference databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Tenants/CurrentTenants/");
                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (int i = 0; i < tenantList.size(); i++) {
                            BillDetails billDetails = dataSnapshot.child(tenantList.get(i).id).child("Accounts").child("Bills").child(dateString).getValue(BillDetails.class);
                            billList.add(billDetails);
                        }

                        for (int i = 0; i < billList.size(); i++) {
                            BillDetails billDetails = billList.get(i);
                            switch (billDetails.getCategory()) {
                                case "Electricity":
                                    electricityBillList.add(billDetails);
                                    break;
                                case "Wifi":
                                    wifiBillList.add(billDetails);
                                    break;
                                case "Gas":
                                    gasBillList.add(billDetails);
                                    break;
                                case "Other":
                                    otherBillList.add(billDetails);
                                    break;
                            }
                        }

                        billCollectionDetailList = new BillCollectionDetailList(electricityBillList, wifiBillList, gasBillList, otherBillList, tenantList, context);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                        billRecyclerView.setLayoutManager(layoutManager);
                        billRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        billRecyclerView.setAdapter(billCollectionDetailList);
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


        return view;
    }
}