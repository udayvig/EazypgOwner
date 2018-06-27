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

        bathroomButton = findViewById(R.id.bathroomButton);
        bioButton = findViewById(R.id.bioButton);
        pgNameButton = findViewById(R.id.pgNameButton);
        roomsButton = findViewById(R.id.roomsButton);
        staffCountButton = findViewById(R.id.staffCountButton);
        landDistButton = findViewById(R.id.landDistButton);
        lastEntryButton = findViewById(R.id.lastEntryButton);
        emailButton = findViewById(R.id.mailButton);
        genderButton = findViewById(R.id.genderButton);
        occupancyButton = findViewById(R.id.occupancyButton);
        contactButton = findViewById(R.id.contactButton);
        ownerNameButton = findViewById(R.id.ownerNameButton);
        locationButton = findViewById(R.id.locationButton);




    }
}
