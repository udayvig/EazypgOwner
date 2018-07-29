package com.example.EazyPG.owner.Activities;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.EazyPG.owner.DetailList.RoomsDetailList;
import com.example.EazyPG.owner.DetailsClasses.ACDetails;
import com.example.EazyPG.owner.DetailsClasses.CCTVDetails;
import com.example.EazyPG.owner.DetailsClasses.D2HDetails;
import com.example.EazyPG.owner.DetailsClasses.DishwasherDetails;
import com.example.EazyPG.owner.DetailsClasses.FanDetails;
import com.example.EazyPG.owner.DetailsClasses.GeyserDetails;
import com.example.EazyPG.owner.DetailsClasses.HeaterDetails;
import com.example.EazyPG.owner.DetailsClasses.InductionDetails;
import com.example.EazyPG.owner.DetailsClasses.IronDetails;
import com.example.EazyPG.owner.DetailsClasses.MicrowaveDetails;
import com.example.EazyPG.owner.DetailsClasses.OtherApplianceDetails;
import com.example.EazyPG.owner.DetailsClasses.RODetails;
import com.example.EazyPG.owner.DetailsClasses.RefrigeratorDetails;
import com.example.EazyPG.owner.DetailsClasses.RouterDetails;
import com.example.EazyPG.owner.DetailsClasses.TVDetails;
import com.example.EazyPG.owner.DetailsClasses.WashingMachineDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class RoomsActivity extends AppCompatActivity {

    ImageView addRoom;
    EditText roomEditText;

    LayoutInflater inflater;

    RadioGroup radioGroup;
    RadioButton radioButton;

    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference, databaseReference1;

    List<String> rooms;

    List<ACDetails> acList;
    List<FanDetails> fanList;
    List<GeyserDetails> geyserList;
    List<WashingMachineDetails> washingMachineList;
    List<RODetails> roList;
    List<DishwasherDetails> dishwasherList;
    List<MicrowaveDetails> microwaveList;
    List<RefrigeratorDetails> refrigeratorList;
    List<TVDetails> tvList;
    List<CCTVDetails> cctvList;
    List<IronDetails> ironList;
    List<InductionDetails> inductionList;
    List<RouterDetails> routerList;
    List<HeaterDetails> heaterList;
    List<D2HDetails> d2HList;
    List<OtherApplianceDetails> otherList;

    ListView listView;
    View emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        Toolbar toolbar = findViewById(R.id.roomsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addRoom = findViewById(R.id.addRoom);

        inflater = getLayoutInflater();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();
        databaseReference1 = firebaseDatabase.getReference();

        rooms = new ArrayList<>();

        acList = new ArrayList<>();
        fanList = new ArrayList<>();
        geyserList = new ArrayList<>();
        washingMachineList = new ArrayList<>();
        roList = new ArrayList<>();
        dishwasherList = new ArrayList<>();
        microwaveList = new ArrayList<>();
        refrigeratorList = new ArrayList<>();
        tvList = new ArrayList<>();
        cctvList = new ArrayList<>();
        ironList = new ArrayList<>();
        inductionList = new ArrayList<>();
        routerList = new ArrayList<>();
        heaterList = new ArrayList<>();
        d2HList = new ArrayList<>();
        otherList = new ArrayList<>();

        listView = findViewById(R.id.listViewRooms);
        emptyList = findViewById(R.id.emptyListRooms);
        listView.setEmptyView(emptyList);

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Rooms");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                rooms.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String room = snapshot.getKey();
                    rooms.add(room);

                }

                RoomsDetailList adapter = new RoomsDetailList(RoomsActivity.this, rooms);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View viewDialog = inflater.inflate(R.layout.dialog_room, null);
                radioGroup = viewDialog.findViewById(R.id.radioGroup);
                roomEditText = viewDialog.findViewById(R.id.roomNoEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(RoomsActivity.this);
                builder.setTitle("Add Room");
                builder.setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int selectedButtonId = radioGroup.getCheckedRadioButtonId();
                        radioButton = viewDialog.findViewById(selectedButtonId);

                        String room = roomEditText.getText().toString();
                        String roomType = radioButton.getText().toString();

                        getDetails(room, roomType);

                    }
                });

                builder.setNegativeButton("Cancel", null);
                builder.show();
            }

        });

    }

    private void getDetails(final String room, final String roomType) {

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/AC/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                acList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    ACDetails acDetails = snapshot.getValue(ACDetails.class);
                    acList.add(acDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < acList.size(); i++) {

                    if (acList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(acList.get(i).roomNo).child("Appliance").child(key).setValue(acList.get(i));
                        databaseReference1.child("Rooms").child(acList.get(i).roomNo).child("Room Type").setValue(roomType);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/Fan/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                fanList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    FanDetails fanDetails = snapshot.getValue(FanDetails.class);
                    fanList.add(fanDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < fanList.size(); i++) {

                    if (fanList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(fanList.get(i).roomNo).child("Appliance").child(key).setValue(fanList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/Geyser/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                geyserList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    GeyserDetails geyserDetails = snapshot.getValue(GeyserDetails.class);
                    geyserList.add(geyserDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < geyserList.size(); i++) {

                    if (geyserList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(geyserList.get(i).roomNo).child("Appliance").child(key).setValue(geyserList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/Washing Machine/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                washingMachineList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    WashingMachineDetails washingMachineDetails = snapshot.getValue(WashingMachineDetails.class);
                    washingMachineList.add(washingMachineDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < washingMachineList.size(); i++) {

                    if (washingMachineList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(washingMachineList.get(i).roomNo).child("Appliance").child(key).setValue(washingMachineList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/RO/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                roList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    RODetails roDetails = snapshot.getValue(RODetails.class);
                    roList.add(roDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < roList.size(); i++) {

                    if (roList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(roList.get(i).roomNo).child("Appliance").child(key).setValue(roList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/Dishwasher/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dishwasherList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    DishwasherDetails dishwasherDetails = snapshot.getValue(DishwasherDetails.class);
                    dishwasherList.add(dishwasherDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < dishwasherList.size(); i++) {

                    if (dishwasherList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(dishwasherList.get(i).roomNo).child("Appliance").child(key).setValue(dishwasherList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/Microwave/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                microwaveList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    MicrowaveDetails microwaveDetails = snapshot.getValue(MicrowaveDetails.class);
                    microwaveList.add(microwaveDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < microwaveList.size(); i++) {

                    if (microwaveList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(microwaveList.get(i).roomNo).child("Appliance").child(key).setValue(microwaveList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/Refrigerator/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                refrigeratorList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    RefrigeratorDetails refrigeratorDetails = snapshot.getValue(RefrigeratorDetails.class);
                    refrigeratorList.add(refrigeratorDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < refrigeratorList.size(); i++) {

                    if (refrigeratorList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(refrigeratorList.get(i).roomNo).child("Appliance").child(key).setValue(refrigeratorList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/TV/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tvList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    TVDetails tvDetails = snapshot.getValue(TVDetails.class);
                    tvList.add(tvDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < tvList.size(); i++) {

                    if (tvList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(tvList.get(i).roomNo).child("Appliance").child(key).setValue(tvList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/CCTV/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                cctvList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    CCTVDetails cctvDetails = snapshot.getValue(CCTVDetails.class);
                    cctvList.add(cctvDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < cctvList.size(); i++) {

                    if (cctvList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(cctvList.get(i).roomNo).child("Appliance").child(key).setValue(cctvList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/Iron/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ironList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    IronDetails ironDetails = snapshot.getValue(IronDetails.class);
                    ironList.add(ironDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < ironList.size(); i++) {

                    if (ironList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(ironList.get(i).roomNo).child("Appliance").child(key).setValue(ironList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/Induction/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                inductionList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    InductionDetails inductionDetails = snapshot.getValue(InductionDetails.class);
                    inductionList.add(inductionDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < inductionList.size(); i++) {

                    if (inductionList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(inductionList.get(i).roomNo).child("Appliance").child(key).setValue(inductionList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/Router/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                routerList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    RouterDetails routerDetails = snapshot.getValue(RouterDetails.class);
                    routerList.add(routerDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < routerList.size(); i++) {

                    if (routerList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(routerList.get(i).roomNo).child("Appliance").child(key).setValue(routerList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/Heater/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                heaterList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    HeaterDetails heaterDetails = snapshot.getValue(HeaterDetails.class);
                    heaterList.add(heaterDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < heaterList.size(); i++) {

                    if (heaterList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(heaterList.get(i).roomNo).child("Appliance").child(key).setValue(heaterList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/D2H/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                d2HList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    D2HDetails d2HDetails = snapshot.getValue(D2HDetails.class);
                    d2HList.add(d2HDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < d2HList.size(); i++) {

                    if (d2HList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(d2HList.get(i).roomNo).child("Appliance").child(key).setValue(d2HList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Appliances/Other/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                otherList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    OtherApplianceDetails otherApplianceDetails = snapshot.getValue(OtherApplianceDetails.class);
                    otherList.add(otherApplianceDetails);

                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                for (int i = 0; i < otherList.size(); i++) {

                    if (otherList.get(i).roomNo.equals(room)) {

                        String key = databaseReference.push().getKey();
                        databaseReference1.child("Rooms").child(otherList.get(i).roomNo).child("Appliance").child(key).setValue(otherList.get(i));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
