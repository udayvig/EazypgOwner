package com.example.EazyPG.owner.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EazyPG.owner.DetailsClasses.CashflowDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExpenseActivity extends AppCompatActivity {

    ListView listView;
    List<CashflowDetails> cashflowDetailsList;


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    EditText amount, category, description, paidBy, paidTo;

    View view;
    View emptyList;

    LayoutInflater inflater;
    CardView servicesCardView, groceryCardView, staffCardView, maintenanceCardView, marketingCardView, taxesAmcCardView;

    TextView tenantNameTextView1, tenantNameTextView2, tenantNameTextView3, tenantNameTextView4, tenantNameTextView5
            ,dateTextView1, dateTextView2, dateTextView3, dateTextView4, dateTextView5
            ,categoryTextView1, categoryTextView2, categoryTextView3, categoryTextView4, categoryTextView5
            ,amountTextView1, amountTextView2, amountTextView3, amountTextView4, amountTextView5
            ,roomNumberTenantTextView1, roomNumberTenantTextView2, roomNumberTenantTextView3, roomNumberTenantTextView4, roomNumberTenantTextView5
            ,descriptionTetView1, descriptionTetView2, descriptionTetView3, descriptionTetView4, descriptionTetView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        servicesCardView = findViewById(R.id.servicesCardView);
        groceryCardView = findViewById(R.id.groceryCardView);
        staffCardView = findViewById(R.id.staffCardView);
        maintenanceCardView = findViewById(R.id.maintenanceCardView);
        marketingCardView = findViewById(R.id.marketingCardView);
        taxesAmcCardView = findViewById(R.id.taxesAmcCardView);

        tenantNameTextView1 = findViewById(R.id.tenantNameTextView1);
        tenantNameTextView2 = findViewById(R.id.tenantNameTextView2);
        tenantNameTextView3 = findViewById(R.id.tenantNameTextView3);
        tenantNameTextView4 = findViewById(R.id.tenantNameTextView4);
        tenantNameTextView5 = findViewById(R.id.tenantNameTextView5);

        dateTextView1 = findViewById(R.id.dateTextView1);
        dateTextView2 = findViewById(R.id.dateTextView2);
        dateTextView3 = findViewById(R.id.dateTextView3);
        dateTextView4 = findViewById(R.id.dateTextView4);
        dateTextView5 = findViewById(R.id.dateTextView5);

        categoryTextView1 = findViewById(R.id.categoryTextView1);
        categoryTextView2 = findViewById(R.id.categoryTextView2);
        categoryTextView3 = findViewById(R.id.categoryTextView3);
        categoryTextView4 = findViewById(R.id.categoryTextView4);
        categoryTextView5 = findViewById(R.id.categoryTextView5);

        amountTextView1 = findViewById(R.id.amountTextView1);
        amountTextView2 = findViewById(R.id.amountTextView2);
        amountTextView3 = findViewById(R.id.amountTextView3);
        amountTextView4 = findViewById(R.id.amountTextView4);
        amountTextView5 = findViewById(R.id.amountTextView5);

        roomNumberTenantTextView1 = findViewById(R.id.roomNumberTenantTextView1);
        roomNumberTenantTextView2 = findViewById(R.id.roomNumberTenantTextView2);
        roomNumberTenantTextView3 = findViewById(R.id.roomNumberTenantTextView3);
        roomNumberTenantTextView4 = findViewById(R.id.roomNumberTenantTextView4);
        roomNumberTenantTextView5 = findViewById(R.id.roomNumberTenantTextView5);

        descriptionTetView1 = findViewById(R.id.descriptionTextView1);
        descriptionTetView2 = findViewById(R.id.descriptionTextView2);
        descriptionTetView3 = findViewById(R.id.descriptionTextView3);
        descriptionTetView4 = findViewById(R.id.descriptionTextView4);
        descriptionTetView5 = findViewById(R.id.descriptionTextView5);

        inflater = getLayoutInflater();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        servicesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = inflater.inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
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
                        String categoryString = "Services";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());
                        String uidExpense = databaseReference.push().getKey();

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
        });

        groceryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = inflater.inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
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
                        String categoryString = "Grocery";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());
                        String uidExpense = databaseReference.push().getKey();

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
        });

        staffCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = inflater.inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
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
                        String categoryString = "Staff";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());
                        String uidExpense = databaseReference.push().getKey();

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
        });

        maintenanceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = inflater.inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
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
                        String categoryString = "Maintenance";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());
                        String uidExpense = databaseReference.push().getKey();

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
        });

        marketingCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = inflater.inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
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
                        String categoryString = "Marketing";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());
                        String uidExpense = databaseReference.push().getKey();

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
        });
        taxesAmcCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = inflater.inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
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
                        String categoryString = "Taxes and AMC";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());
                        String uidExpense = databaseReference.push().getKey();

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
        });
        /*Toolbar toolbar = findViewById(R.id.expenseToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        listView = findViewById(R.id.listViewExpenses);
        emptyList = findViewById(R.id.emptyListExpenses);
        listView.setEmptyView(emptyList);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Cashflow/");

        inflater = getLayoutInflater();

        listView = findViewById(R.id.listViewExpenses);
//      addExpense = findViewById(R.id.addExpense);
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
        });*/

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
