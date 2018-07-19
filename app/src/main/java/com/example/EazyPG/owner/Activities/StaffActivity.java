package com.example.EazyPG.owner.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EazyPG.owner.StaffDetailList;
import com.example.EazyPG.owner.StaffDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StaffActivity extends AppCompatActivity {

    ListView listView;
    List<StaffDetails> staffDetailsList;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    ImageView addStaff;
    EditText staffName, jobDesc, contact, salary, dateOfJoining;
    TextView addStaffTitle;

    Snackbar snackbar;
    View view;

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        Toolbar toolbar = findViewById(R.id.staffToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = firebaseDatabase.getReference("PG/"+firebaseUser.getUid() + "/Staff/");

        inflater = getLayoutInflater();

        listView = findViewById(R.id.listView);
        addStaff = findViewById(R.id.addStaff);
        view = findViewById(R.id.stafflayout);

        staffName = findViewById(R.id.staffNameEditText);
        jobDesc = findViewById(R.id.jobDescEditText);
        contact = findViewById(R.id.contactEditText);
        salary = findViewById(R.id.salaryEditText);
        dateOfJoining = findViewById(R.id.dateOfJoiningEditText);

        snackbar = Snackbar.make(view, "Tap item to edit and hold to delete", Snackbar.LENGTH_LONG);
        snackbar.show();

        staffDetailsList = new ArrayList<>();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                staffDetailsList.clear();

                for (DataSnapshot dataSnapshotStaff : dataSnapshot.getChildren()) {

                    StaffDetails staffDetails = dataSnapshotStaff.getValue(StaffDetails.class);
                    staffDetailsList.add(staffDetails);
                }

                StaffDetailList adapter = new StaffDetailList(StaffActivity.this, staffDetailsList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View viewDialog = inflater.inflate(R.layout.dialog_staff, null);

                staffName = viewDialog.findViewById(R.id.staffNameEditText);
                jobDesc = viewDialog.findViewById(R.id.jobDescEditText);
                contact = viewDialog.findViewById(R.id.contactEditText);
                salary = viewDialog.findViewById(R.id.salaryEditText);
                dateOfJoining = viewDialog.findViewById(R.id.dateOfJoiningEditText);

                final View addTitleView = inflater.inflate(R.layout.custom_title2, null);
                addStaffTitle = addTitleView.findViewById(R.id.addStaffCustomTitle);

                AlertDialog.Builder builder = new AlertDialog.Builder(StaffActivity.this);
                builder.setCustomTitle(addStaffTitle);

                builder.setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final ProgressDialog progressDialog = ProgressDialog.show(StaffActivity.this, "", "Saving...", true);


                        String staffNameString = staffName.getText().toString();
                        String jobDescString = jobDesc.getText().toString();
                        String contactString = contact.getText().toString();
                        String salaryString = salary.getText().toString();
                        String dateOfJoiningString = dateOfJoining.getText().toString();
                        String uidStaff = databaseReference.push().getKey();

                        databaseReference = firebaseDatabase.getReference("PG/"+firebaseUser.getUid());

                        if (staffNameString.equals("")) {
                            Toast.makeText(StaffActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                        else {

                            StaffDetails staffDetails1 = new StaffDetails(uidStaff, salaryString, contactString, staffNameString, jobDescString, dateOfJoiningString);
                            databaseReference.child("Staff").child(uidStaff).setValue(staffDetails1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(StaffActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(StaffActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });

                builder.setNegativeButton("Cancel", null);
                builder.show();

            }
        });

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(StaffActivity.this, HomePageActivity.class));
        finish();
    }
}
