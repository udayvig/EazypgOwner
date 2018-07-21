package com.example.EazyPG.owner.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.EazyPG.owner.DetailsClasses.CashflowDetails;
import com.example.EazyPG.owner.DetailList.PassbookDetailList;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PassbookActivity extends AppCompatActivity {

    ListView listView;
    List<CashflowDetails> passbookDetailsList;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    EditText amount, category, description, paidBy, paidTo;
    ImageView addInflow, addOutflow;

    View view1;

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passbook);

        Toolbar toolbar = findViewById(R.id.passbookToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Cashflow/");

        inflater = getLayoutInflater();

        listView = findViewById(R.id.listViewPassbook);
        addInflow = findViewById(R.id.inflow);
        addOutflow = findViewById(R.id.outflow);
        view1 = findViewById(R.id.passbooklayout);

        passbookDetailsList = new ArrayList<>();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                passbookDetailsList.clear();

                for(DataSnapshot dataSnapshotExpense : dataSnapshot.getChildren()){
                    CashflowDetails passbookDetails = dataSnapshotExpense.getValue(CashflowDetails.class);
                    passbookDetailsList.add(passbookDetails);
                }

                PassbookDetailList adapter = new PassbookDetailList(PassbookActivity.this, passbookDetailsList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addOutflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = inflater.inflate(R.layout.dialog_passbook_outflow, null);

                amount = viewDialog.findViewById(R.id.expenseAmountEditText);
                category = viewDialog.findViewById(R.id.categoryEditText);
                description = viewDialog.findViewById(R.id.descriptionEditText);
                paidBy = viewDialog.findViewById(R.id.paidByEditText);
                paidTo = viewDialog.findViewById(R.id.paidToEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(PassbookActivity.this);
                builder.setTitle("Add Outflow Details")
                        .setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final ProgressDialog progressDialog = ProgressDialog.show(PassbookActivity.this, "", "Saving...", true);

                        String amountString = amount.getText().toString();
                        String categoryString = category.getText().toString();
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();
                        String uidOutflow = databaseReference.push().getKey();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                        if(amountString.isEmpty()){
                            progressDialog.dismiss();
                            Toast.makeText(PassbookActivity.this, "Amount Required!", Toast.LENGTH_SHORT).show();
                        }else {
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            Date date = new Date();
                            String dateString = dateFormat.format(date);

                            CashflowDetails passbookDetails1 = new CashflowDetails(uidOutflow, amountString, categoryString, descriptionString, paidByString, paidToString, dateString, false);
                            databaseReference.child("Cashflow").child(uidOutflow).setValue(passbookDetails1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(PassbookActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(PassbookActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                                }
                            });
                            progressDialog.dismiss();
                        }
                        progressDialog.dismiss();
                    }
                });

                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });

        addInflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = inflater.inflate(R.layout.dialog_passbook_inflow, null);

                amount = viewDialog.findViewById(R.id.inflowAmountEditText);
                category = viewDialog.findViewById(R.id.inflowCategoryEditText);
                description = viewDialog.findViewById(R.id.inflowDescriptionEditText);
                paidBy = viewDialog.findViewById(R.id.inflowPaidByEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(PassbookActivity.this);
                builder.setTitle("Add Inflow Details")
                        .setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final ProgressDialog progressDialog = ProgressDialog.show(PassbookActivity.this, "", "Saving...", true);

                        String amountString = amount.getText().toString();
                        String categoryString = category.getText().toString();
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();

                        String uidInflow = databaseReference.push().getKey();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                        if(amountString.isEmpty()){
                            progressDialog.dismiss();
                            Toast.makeText(PassbookActivity.this, "Amount Required!", Toast.LENGTH_SHORT).show();
                        }else {
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            Date date = new Date();
                            String dateString = dateFormat.format(date);

                            CashflowDetails passbookDetails2 = new CashflowDetails(uidInflow, amountString, categoryString, descriptionString, paidByString, dateString, true);
                            databaseReference.child("Cashflow").child(uidInflow).setValue(passbookDetails2).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(PassbookActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(PassbookActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(PassbookActivity.this, HomePageActivity.class));
        finish();
    }
}
