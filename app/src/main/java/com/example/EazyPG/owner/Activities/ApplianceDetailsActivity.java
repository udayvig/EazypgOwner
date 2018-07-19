package com.example.EazyPG.owner.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EazyPG.owner.DetailList.D2HDetailList;
import com.example.EazyPG.owner.DetailList.OtherDetailList;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailAC;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailCCTV;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailD2H;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailDishwasher;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailFan;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailGeyser;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailHeater;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailInduction;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailIron;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailLift;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailMicrowave;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailOther;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailRO;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailRefrigerator;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailRouter;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailTV;
import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailWashingMachine;
import com.example.EazyPG.owner.DetailList.ACDetailList;
import com.example.EazyPG.owner.DetailList.CCTVDetailList;
import com.example.EazyPG.owner.DetailList.DishwasherDetailList;
import com.example.EazyPG.owner.DetailList.FanDetailList;
import com.example.EazyPG.owner.DetailList.GeyserDetailList;
import com.example.EazyPG.owner.DetailList.HeaterDetailList;
import com.example.EazyPG.owner.DetailList.InductionDetailList;
import com.example.EazyPG.owner.DetailList.IronDetailList;
import com.example.EazyPG.owner.DetailList.LiftDetailList;
import com.example.EazyPG.owner.DetailList.MicrowaveDetailList;
import com.example.EazyPG.owner.DetailList.RODetailList;
import com.example.EazyPG.owner.DetailList.RefrigeratorDetailList;
import com.example.EazyPG.owner.DetailList.RouterDetailList;
import com.example.EazyPG.owner.DetailList.TVDetailList;
import com.example.EazyPG.owner.DetailList.WashingMachineDetailList;
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

public class ApplianceDetailsActivity extends AppCompatActivity {

    ListView listView;

    List<ApplianceDetailAC> acList;
    List<ApplianceDetailFan> fanList;
    List<ApplianceDetailLift> liftList;
    List<ApplianceDetailGeyser> geyserList;
    List<ApplianceDetailWashingMachine> washingMachineList;
    List<ApplianceDetailRO> roList;
    List<ApplianceDetailDishwasher> dishwasherList;
    List<ApplianceDetailMicrowave> microwaveList;
    List<ApplianceDetailRefrigerator> refrigeratorList;
    List<ApplianceDetailTV> tvList;
    List<ApplianceDetailCCTV> cctvList;
    List<ApplianceDetailIron> ironList;
    List<ApplianceDetailInduction> inductionList;
    List<ApplianceDetailRouter> routerList;
    List<ApplianceDetailHeater> heaterList;
    List<ApplianceDetailD2H> d2HList;
    List<ApplianceDetailOther> otherList;


    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    Snackbar snackbar;
    View view;
    View emptyList;
    private Intent intent;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_details);

        Toolbar toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView toolbarTitle = findViewById(R.id.toolbarText);

        listView = findViewById(R.id.listView);
        emptyList = findViewById(R.id.emptyList);
        listView.setEmptyView(emptyList);

        view = findViewById(R.id.applianceNameLayout);

        snackbar = Snackbar.make(view, "Tap item to edit and hold to delete", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(ApplianceDetailsActivity.this, R.color.DarkGreen));
        snackbar.show();

        acList = new ArrayList<>();
        fanList = new ArrayList<>();
        liftList = new ArrayList<>();
        geyserList = new ArrayList<>();
        washingMachineList = new ArrayList<>();
        roList = new ArrayList<>();
        dishwasherList = new ArrayList<>();
        microwaveList = new ArrayList<>();
        refrigeratorList = new ArrayList<>();
        refrigeratorList = new ArrayList<>();
        tvList = new ArrayList<>();
        cctvList = new ArrayList<>();
        ironList = new ArrayList<>();
        inductionList = new ArrayList<>();
        routerList = new ArrayList<>();
        heaterList = new ArrayList<>();
        d2HList = new ArrayList<>();
        otherList = new ArrayList<>();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = "PG/"+firebaseUser.getUid();

        Intent intent = getIntent();
        String message = intent.getStringExtra(ApplianceAdapter.EXTRA_MESSAGE);

        switch (message) {
            case "AC":
                toolbarTitle.setText("AC");
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

                break;
            case "Fan":
                toolbarTitle.setText("Fan");
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
                break;
            case "Lift":
                toolbarTitle.setText("Lift");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Lift");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        liftList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailLift applianceDetailLift = dataSnapshotAC.getValue(ApplianceDetailLift.class);
                            liftList.add(applianceDetailLift);
                        }

                        LiftDetailList adapter = new LiftDetailList(ApplianceDetailsActivity.this, liftList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                break;
            case "Geyser":
                toolbarTitle.setText("Geyser");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Geyser");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        geyserList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailGeyser applianceDetailGeyser = dataSnapshotAC.getValue(ApplianceDetailGeyser.class);
                            geyserList.add(applianceDetailGeyser);
                        }

                        GeyserDetailList adapter = new GeyserDetailList(ApplianceDetailsActivity.this, geyserList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Washing Machine":
                toolbarTitle.setText("Washing Machine");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Washing Machine");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        washingMachineList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailWashingMachine applianceDetailWashingMachine = dataSnapshotAC.getValue(ApplianceDetailWashingMachine.class);
                            washingMachineList.add(applianceDetailWashingMachine);
                        }

                        WashingMachineDetailList adapter = new WashingMachineDetailList(ApplianceDetailsActivity.this, washingMachineList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "RO":
                toolbarTitle.setText("RO");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/RO");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        roList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailRO applianceDetailRO = dataSnapshotAC.getValue(ApplianceDetailRO.class);
                            roList.add(applianceDetailRO);
                        }

                        RODetailList adapter = new RODetailList(ApplianceDetailsActivity.this, roList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Dishwasher":
                toolbarTitle.setText("Dishwasher");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Dishwasher");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dishwasherList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailDishwasher applianceDetailDishwasher = dataSnapshotAC.getValue(ApplianceDetailDishwasher.class);
                            dishwasherList.add(applianceDetailDishwasher);
                        }

                        DishwasherDetailList adapter = new DishwasherDetailList(ApplianceDetailsActivity.this, dishwasherList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Microwave":
                toolbarTitle.setText("Microwave");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Microwave");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        microwaveList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailMicrowave applianceDetailMicrowave = dataSnapshotAC.getValue(ApplianceDetailMicrowave.class);
                            microwaveList.add(applianceDetailMicrowave);
                        }

                        MicrowaveDetailList adapter = new MicrowaveDetailList(ApplianceDetailsActivity.this, microwaveList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Refrigerator":
                toolbarTitle.setText("Refrigerator");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Refrigerator");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        refrigeratorList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailRefrigerator applianceDetailRefrigerator = dataSnapshotAC.getValue(ApplianceDetailRefrigerator.class);
                            refrigeratorList.add(applianceDetailRefrigerator);
                        }

                        RefrigeratorDetailList adapter = new RefrigeratorDetailList(ApplianceDetailsActivity.this, refrigeratorList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "TV":
                toolbarTitle.setText("TV");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/TV");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        tvList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailTV applianceDetailTV = dataSnapshotAC.getValue(ApplianceDetailTV.class);
                            tvList.add(applianceDetailTV);
                        }

                        TVDetailList adapter = new TVDetailList(ApplianceDetailsActivity.this, tvList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "CCTV":
                toolbarTitle.setText("CCTV");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/CCTV");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        cctvList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailCCTV applianceDetailCCTV = dataSnapshotAC.getValue(ApplianceDetailCCTV.class);
                            cctvList.add(applianceDetailCCTV);
                        }

                        CCTVDetailList adapter = new CCTVDetailList(ApplianceDetailsActivity.this, cctvList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Iron":
                toolbarTitle.setText("Iron");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Iron");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        ironList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailIron applianceDetailIron = dataSnapshotAC.getValue(ApplianceDetailIron.class);
                            ironList.add(applianceDetailIron);
                        }

                        IronDetailList adapter = new IronDetailList(ApplianceDetailsActivity.this, ironList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Induction":
                toolbarTitle.setText("Induction");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Induction");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        inductionList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailInduction applianceDetailInduction = dataSnapshotAC.getValue(ApplianceDetailInduction.class);
                            inductionList.add(applianceDetailInduction);
                        }

                        InductionDetailList adapter = new InductionDetailList(ApplianceDetailsActivity.this, inductionList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Router":
                toolbarTitle.setText("Router");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Router");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        routerList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailRouter applianceDetailRouter = dataSnapshotAC.getValue(ApplianceDetailRouter.class);
                            routerList.add(applianceDetailRouter);
                        }

                        RouterDetailList adapter = new RouterDetailList(ApplianceDetailsActivity.this, routerList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Heater":
                toolbarTitle.setText("Heater");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Heater");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        heaterList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailHeater applianceDetailHeater = dataSnapshotAC.getValue(ApplianceDetailHeater.class);
                            heaterList.add(applianceDetailHeater);
                        }

                        HeaterDetailList adapter = new HeaterDetailList(ApplianceDetailsActivity.this, heaterList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "D2H":
                toolbarTitle.setText("D2H");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/D2H");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        d2HList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailD2H applianceDetailD2H = dataSnapshotAC.getValue(ApplianceDetailD2H.class);
                            d2HList.add(applianceDetailD2H);
                        }

                        D2HDetailList adapter = new D2HDetailList(ApplianceDetailsActivity.this, d2HList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Other":
                toolbarTitle.setText("Other Appliance");
                databaseReference = FirebaseDatabase.getInstance().getReference(uid + "/Appliances/Other");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        otherList.clear();

                        for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                            ApplianceDetailOther applianceDetailOther = dataSnapshotAC.getValue(ApplianceDetailOther.class);
                            otherList.add(applianceDetailOther);
                        }

                        OtherDetailList adapter = new OtherDetailList(ApplianceDetailsActivity.this, otherList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
        }

    }

}