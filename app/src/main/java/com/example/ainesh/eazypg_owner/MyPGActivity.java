package com.example.ainesh.eazypg_owner;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyPGActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    FloatingActionButton saveButton;

    EditText noOfBathEditText;
    EditText noOfRoomsEditText;;
    EditText staffNumberEditText;
    EditText occupancyEditText;
    EditText genderEditText;
    EditText locationEditText;
    EditText lastEntryEditText;
    EditText distLandEditText;
    EditText ownerNameEditText;
    EditText contactEditText;
    EditText pgNameEditText;
    EditText bioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pg);

        noOfBathEditText = findViewById(R.id.noOfBathEditText);
        noOfRoomsEditText = findViewById(R.id.noOfRoomsEditText);
        staffNumberEditText = findViewById(R.id.staffNumberEditText);
        occupancyEditText = findViewById(R.id.occupancyEditText);
        genderEditText = findViewById(R.id.genderEditText);
        locationEditText = findViewById(R.id.locationEditText);
        lastEntryEditText = findViewById(R.id.lastEntryEditText);
        distLandEditText = findViewById(R.id.distLandEditText);
        ownerNameEditText = findViewById(R.id.ownerNameEditText);
        contactEditText = findViewById(R.id.contactEditText);
        pgNameEditText = findViewById(R.id.pgNameEditText);
        bioEditText = findViewById(R.id.bioEditText);

        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingPg();
            }
        });

    }

    private void addingPg() {

        String pgName = pgNameEditText.getText().toString().trim();
        String bio = bioEditText.getText().toString().trim();
        String Location = locationEditText.getText().toString().trim();
        String ownerName = ownerNameEditText.getText().toString().trim();
        String pgContact = contactEditText.getText().toString().trim();
        String Landmark = distLandEditText.getText().toString().trim();
        String lastEntry = lastEntryEditText.getText().toString().trim();
        String Gender = genderEditText.getText().toString().trim();
        String occupancy = occupancyEditText.getText().toString().trim();
        String StaffCount = staffNumberEditText.getText().toString().trim();
        String room = noOfRoomsEditText.getText().toString().trim();
        String bath = noOfBathEditText.getText().toString().trim();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        databaseReference = FirebaseDatabase.getInstance().getReference("MyPG").child(uid);

        PG pg = new PG(pgName, bio, Location, ownerName, pgContact, Landmark, lastEntry, Gender, occupancy, StaffCount, room, bath);
        databaseReference.child("PG Details").setValue(pg);
    }
}
