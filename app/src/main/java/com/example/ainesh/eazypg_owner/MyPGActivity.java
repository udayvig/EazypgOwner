package com.example.ainesh.eazypg_owner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class MyPGActivity extends AppCompatActivity {

    ImageButton bathroomButton;
    ImageButton bioButton;
    ImageButton pgNameButton;
    ImageButton roomsButton;
    ImageButton staffCountButton;
    ImageButton landDistButton;
    ImageButton lastEntryButton;
    ImageButton emailButton;
    ImageButton genderButton;
    ImageButton occupancyButton;
    ImageButton contactButton;
    ImageButton ownerNameButton;
    ImageButton locationButton;

    EditText noOfBathEditText;
    EditText noOfRoomsEditText;;
    EditText staffNumberEditText;
    EditText occupancyEditText;
    EditText genderEditText;
    EditText locationEditText;
    EditText lastEntryEditText;
    EditText pgEmailEditText;
    EditText distLandEditText;
    EditText ownerNameEditText;
    EditText contactEditText;
    EditText pgNameEditText;
    EditText bioEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pg);

        noOfBathEditText=findViewById(R.id.noOfBathEditText);
        noOfRoomsEditText=findViewById(R.id.noOfRoomsEditText);
        staffNumberEditText=findViewById(R.id.staffNumberEditText);
        occupancyEditText=findViewById(R.id.occupancyEditText);
        genderEditText=findViewById(R.id.genderEditText);
        locationEditText=findViewById(R.id.locationEditText);
        lastEntryEditText=findViewById(R.id.lastEntryEditText);
        pgEmailEditText=findViewById(R.id.pgEmailEditText);
        distLandEditText=findViewById(R.id.distLandEditText);
        ownerNameEditText=findViewById(R.id.ownerNameEditText);
        contactEditText=findViewById(R.id.contactEditText);
        pgNameEditText=findViewById(R.id.pgNameEditText);
        bioEditText=findViewById(R.id.bioEditText);
    }
}
