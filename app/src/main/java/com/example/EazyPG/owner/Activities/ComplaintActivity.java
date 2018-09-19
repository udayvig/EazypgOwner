package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.fabric.sdk.android.Fabric;

public class ComplaintActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<ComplaintDetails> complaintDetailsList;

    CardView bedroomComplaint, foodComplaint, facilityComplaint, securityComplaint;

    HorizontalScrollView horizontalScrollView;

    Date date;
    DateFormat dateFormat;

    ImageView backButton;

    CardView firstComplaintCard, secondComplaintCard, thirdComplaintCard, fourthComplaintCard, fifthComplaintCard;

    boolean flag;

    TextView dateTextView1, complaintIdTextView1, categoryTextView1, subCategoryTextView1, subSubCategoryTextView1, tenantNameTextView1, roomNumberTenantTextView1, descriptionTextView1;
    TextView dateTextView2, complaintIdTextView2, categoryTextView2, subCategoryTextView2, subSubCategoryTextView2, tenantNameTextView2, roomNumberTenantTextView2, descriptionTextView2;
    TextView dateTextView3, complaintIdTextView3, categoryTextView3, subCategoryTextView3, subSubCategoryTextView3, tenantNameTextView3, roomNumberTenantTextView3, descriptionTextView3;
    TextView dateTextView4, complaintIdTextView4, categoryTextView4, subCategoryTextView4, subSubCategoryTextView4, tenantNameTextView4, roomNumberTenantTextView4, descriptionTextView4;
    TextView dateTextView5, complaintIdTextView5, categoryTextView5, subCategoryTextView5, subSubCategoryTextView5, tenantNameTextView5, roomNumberTenantTextView5, descriptionTextView5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        Fabric.with(this, new Crashlytics());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(33,33,33));
        }

        horizontalScrollView = findViewById(R.id.horizontalScrollView);

        dateTextView1 = findViewById(R.id.dateTextView1);
        complaintIdTextView1 = findViewById(R.id.complaintIdTextView1);
        categoryTextView1 = findViewById(R.id.categoryTextView1);
        subCategoryTextView1 = findViewById(R.id.subCategoryTextView1);
        subSubCategoryTextView1 = findViewById(R.id.subSubCategoryTextView1);
        tenantNameTextView1 = findViewById(R.id.tenantNameTextView1);
        roomNumberTenantTextView1 = findViewById(R.id.roomNumberTenantTextView1);
        descriptionTextView1 = findViewById(R.id.descriptionTextView1);

        dateTextView2 = findViewById(R.id.dateTextView2);
        complaintIdTextView2 = findViewById(R.id.complaintIdTextView2);
        categoryTextView2 = findViewById(R.id.categoryTextView2);
        subCategoryTextView2 = findViewById(R.id.subCategoryTextView2);
        subSubCategoryTextView2 = findViewById(R.id.subSubCategoryTextView2);
        tenantNameTextView2 = findViewById(R.id.tenantNameTextView2);
        roomNumberTenantTextView2 = findViewById(R.id.roomNumberTenantTextView2);
        descriptionTextView2 = findViewById(R.id.descriptionTextView2);

        dateTextView3 = findViewById(R.id.dateTextView3);
        complaintIdTextView3 = findViewById(R.id.complaintIdTextView3);
        categoryTextView3 = findViewById(R.id.categoryTextView3);
        subCategoryTextView3 = findViewById(R.id.subCategoryTextView3);
        subSubCategoryTextView3 = findViewById(R.id.subSubCategoryTextView3);
        tenantNameTextView3 = findViewById(R.id.tenantNameTextView3);
        roomNumberTenantTextView3 = findViewById(R.id.roomNumberTenantTextView3);
        descriptionTextView3 = findViewById(R.id.descriptionTextView3);

        dateTextView4 = findViewById(R.id.dateTextView4);
        complaintIdTextView4 = findViewById(R.id.complaintIdTextView4);
        categoryTextView4 = findViewById(R.id.categoryTextView4);
        subCategoryTextView4 = findViewById(R.id.subCategoryTextView4);
        subSubCategoryTextView4 = findViewById(R.id.subSubCategoryTextView4);
        tenantNameTextView4 = findViewById(R.id.tenantNameTextView4);
        roomNumberTenantTextView4 = findViewById(R.id.roomNumberTenantTextView4);
        descriptionTextView4 = findViewById(R.id.descriptionTextView4);

        dateTextView5 = findViewById(R.id.dateTextView5);
        complaintIdTextView5 = findViewById(R.id.complaintIdTextView5);
        categoryTextView5 = findViewById(R.id.categoryTextView5);
        subCategoryTextView5 = findViewById(R.id.subCategoryTextView5);
        subSubCategoryTextView5 = findViewById(R.id.subSubCategoryTextView5);
        tenantNameTextView5 = findViewById(R.id.tenantNameTextView5);
        roomNumberTenantTextView5 = findViewById(R.id.roomNumberTenantTextView5);
        descriptionTextView5 = findViewById(R.id.descriptionTextView5);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        complaintDetailsList = new ArrayList<>();

        bedroomComplaint = findViewById(R.id.bedroomComplaint);
        facilityComplaint = findViewById(R.id.facilityComplaint);
        foodComplaint = findViewById(R.id.foodComplaint);
        securityComplaint = findViewById(R.id.securityComplaint);

        firstComplaintCard = findViewById(R.id.firstComplaintCard);
        secondComplaintCard = findViewById(R.id.secondComplaintCard);
        thirdComplaintCard = findViewById(R.id.thirdComplaintCard);
        fourthComplaintCard = findViewById(R.id.fourthComplaintCard);
        fifthComplaintCard = findViewById(R.id.fifthComplaintCard);

        backButton = findViewById(R.id.imageView3);


        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaint/Bedroom/");

        DatabaseReference databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaints/Bedroom/");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ComplaintDetails complaintDetails1 = snapshot.getValue(ComplaintDetails.class);

                    if (complaintDetails1.status.equals("Unresolved"))
                    complaintDetailsList.add(complaintDetails1);

                }

                flag = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference2 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaints/Mess & Food/");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ComplaintDetails complaintDetails1 = snapshot.getValue(ComplaintDetails.class);

                    if (complaintDetails1.status.equals("Unresolved"))
                    complaintDetailsList.add(complaintDetails1);

                }

                flag = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference3 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaints/Facilities/");
        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ComplaintDetails complaintDetails1 = snapshot.getValue(ComplaintDetails.class);

                    if (complaintDetails1.status.equals("Unresolved"))
                    complaintDetailsList.add(complaintDetails1);

                }

                flag = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference4 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Complaints/Management & Security/");
        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ComplaintDetails complaintDetails1 = snapshot.getValue(ComplaintDetails.class);

                    if (complaintDetails1.status.equals("Unresolved"))
                        complaintDetailsList.add(complaintDetails1);
                }

                flag = true;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (flag) {

            Collections.sort(complaintDetailsList, Collections.<ComplaintDetails>reverseOrder());

            if (complaintDetailsList.size() < 5) {

                int size = complaintDetailsList.size();

                dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

                switch (size) {


                    case 0:
                        horizontalScrollView.setVisibility(View.INVISIBLE);

                        break;

                    case 1:

                        secondComplaintCard.setVisibility(View.INVISIBLE);
                        thirdComplaintCard.setVisibility(View.INVISIBLE);
                        fourthComplaintCard.setVisibility(View.INVISIBLE);
                        fifthComplaintCard.setVisibility(View.INVISIBLE);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(0).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(0).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(0).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(0).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(0).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(0).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(0).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(0).description);

                        break;

                    case 2:
                        thirdComplaintCard.setVisibility(View.INVISIBLE);
                        fourthComplaintCard.setVisibility(View.INVISIBLE);
                        fifthComplaintCard.setVisibility(View.INVISIBLE);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(0).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(0).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(0).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(0).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(0).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(0).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(0).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(0).description);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(1).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(1).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(1).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(1).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(1).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(1).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(1).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(1).description);

                        break;

                    case 3:
                        fourthComplaintCard.setVisibility(View.INVISIBLE);
                        fifthComplaintCard.setVisibility(View.INVISIBLE);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(0).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(0).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(0).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(0).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(0).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(0).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(0).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(0).description);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(1).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(1).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(1).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(1).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(1).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(1).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(1).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(1).description);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(2).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(2).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(2).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(2).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(2).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(2).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(2).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(2).description);

                        break;

                    case 4:
                        fifthComplaintCard.setVisibility(View.INVISIBLE);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(0).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(0).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(0).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(0).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(0).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(0).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(0).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(0).description);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(1).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(1).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(1).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(1).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(1).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(1).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(1).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(1).description);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(2).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(2).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(2).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(2).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(2).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(2).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(2).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(2).description);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(3).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(3).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(3).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(3).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(3).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(3).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(3).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(3).description);

                        break;

                    case 5:

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(0).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(0).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(0).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(0).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(0).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(0).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(0).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(0).description);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(1).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(1).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(1).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(1).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(1).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(1).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(1).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(1).description);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(2).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(2).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(2).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(2).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(2).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(2).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(2).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(2).description);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(3).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(3).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(3).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(3).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(3).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(3).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(3).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(3).description);

                        dateTextView1.setText(dateFormat.format(complaintDetailsList.get(4).date));
                        complaintIdTextView1.setText(complaintDetailsList.get(4).complaintId);
                        categoryTextView1.setText(complaintDetailsList.get(4).firstLevel);
                        subCategoryTextView1.setText(complaintDetailsList.get(4).secondLevel);
                        subSubCategoryTextView1.setText(complaintDetailsList.get(4).thirdLevel);
                        tenantNameTextView1.setText(complaintDetailsList.get(4).name);
                        roomNumberTenantTextView1.setText(complaintDetailsList.get(4).roomNo);
                        descriptionTextView1.setText(complaintDetailsList.get(4).description);

                        break;

                }

            }

        }

        bedroomComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, BedroomComplaintsFragmentActivity.class));
            }
        });

        foodComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, FoodComplaintsFragmentActivity.class));
            }
        });

        facilityComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, FacilityComplaintsFragmentActivity.class));
            }
        });

        securityComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ComplaintActivity.this, SecurityComplaintsFragmentActivity.class));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ComplaintActivity.this, HomePageActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ComplaintActivity.this, HomePageActivity.class));
        finish();
    }

}


