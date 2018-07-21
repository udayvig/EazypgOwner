package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.EazyPG.owner.ExpenseDetailList;
import com.example.EazyPG.owner.CashflowDetails;
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

public class ExpenseActivity extends AppCompatActivity {

    ListView listView;
    List<CashflowDetails> cashflowDetailsList;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    ImageView addExpense;
    EditText amount, category, description, paidBy, paidTo;

    View view;

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        Toolbar toolbar = findViewById(R.id.expenseToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Cashflow/");

        inflater = getLayoutInflater();

        listView = findViewById(R.id.listViewExpenses);
//        addExpense = findViewById(R.id.addExpense);
        view = findViewById(R.id.expenselayout);

        amount = findViewById(R.id.expenseAmountEditText);
        category = findViewById(R.id.categoryEditText);
        description = findViewById(R.id.descriptionEditText);
        paidBy = findViewById(R.id.paidByEditText);
        paidTo = findViewById(R.id.paidToEditText);

        cashflowDetailsList = new ArrayList<>();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cashflowDetailsList.clear();

                for(DataSnapshot dataSnapshotExpense : dataSnapshot.getChildren()){
                    CashflowDetails cashflowDetails = dataSnapshotExpense.getValue(CashflowDetails.class);
                    if(!cashflowDetails.isInout()){
                        cashflowDetailsList.add(cashflowDetails);
                    }
                }

                ExpenseDetailList adapter = new ExpenseDetailList(ExpenseActivity.this, cashflowDetailsList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /* addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = inflater.inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.expenseAmountEditText);
                category = viewDialog.findViewById(R.id.categoryEditText);
                description = viewDialog.findViewById(R.id.descriptionEditText);
                paidBy = viewDialog.findViewById(R.id.paidByEditText);
                paidTo = viewDialog.findViewById(R.id.paidToEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(ExpenseActivity.this);

                builder.setTitle("Add Expense Details")
                        .setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    final ProgressDialog progressDialog = ProgressDialog.show(ExpenseActivity.this, "", "Saving...", true);

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String amountString = amount.getText().toString();
                        String categoryString = category.getText().toString();
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();
                        String uidExpense = databaseReference.push().getKey();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                        if(amountString.isEmpty()){
                            Toast.makeText(ExpenseActivity.this, "Amount Required!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }else{
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            Date date = new Date();
                            String dateString = dateFormat.format(date);
                            CashflowDetails cashflowDetails1 = new CashflowDetails(uidExpense, amountString, categoryString, descriptionString, paidByString, paidToString, dateString, false);
                            databaseReference.child("Cashflow").child(uidExpense).setValue(cashflowDetails1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(ExpenseActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(ExpenseActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ExpenseActivity.this, HomePageActivity.class));
        finish();
    }
}
