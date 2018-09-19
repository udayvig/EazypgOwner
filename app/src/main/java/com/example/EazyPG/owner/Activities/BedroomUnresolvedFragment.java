package com.example.EazyPG.owner.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crashlytics.android.Crashlytics;
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

import io.fabric.sdk.android.Fabric;

public class BedroomUnresolvedFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<ComplaintDetails> complaintDetailsList;

    RecyclerView recyclerView;
    ComplaintDetailList adapter;
     Context context ;
    public BedroomUnresolvedFragment() {

    }
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Fabric.with(getContext(), new Crashlytics());

        view = inflater.inflate(R.layout.bedroom_unresolved_fragment, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        context = getContext();



        complaintDetailsList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.bedroom_unresolved_recycler_view);
        recyclerView.setHasFixedSize(true);

        DatabaseReference databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaints/Bedroom/");
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
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }
}
