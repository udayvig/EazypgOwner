package com.example.EazyPG.owner.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.EazyPG.owner.DetailsClasses.CashflowDetails;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.fabric.sdk.android.Fabric;


public class ExpenseFragment extends Fragment {


    View view2;

    ListView listView;
    List<CashflowDetails> expensesDetailsList = new ArrayList<>();


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    EditText amount, category, description, paidBy, paidTo;

    View emptyList;

    LayoutInflater inflater;
    CardView servicesCardView, groceryCardView, staffCardView, maintenanceCardView, marketingCardView, taxesAmcCardView, firstExpenseCardView, secondExpenseCardView, thirdExpenseCardView, fourthExpenseCardView, fifthExpenseCardView;

    TextView paidByNameTextView1, paidByNameTextView2, paidByNameTextView3, paidByNameTextView4, paidByNameTextView5
            ,dateTextView1, dateTextView2, dateTextView3, dateTextView4, dateTextView5
            ,categoryTextView1, categoryTextView2, categoryTextView3, categoryTextView4, categoryTextView5
            ,amountTextView1, amountTextView2, amountTextView3, amountTextView4, amountTextView5
            ,paidToTextView1, paidToTextView2, paidToTextView3, paidToTextView4, paidToTextView5
            ,descriptionTetView1, descriptionTetView2, descriptionTetView3, descriptionTetView4, descriptionTetView5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Fabric.with(getContext(), new Crashlytics());

        view2 = inflater.inflate(R.layout.activity_expenses, container, false);

        servicesCardView = view2.findViewById(R.id.servicesCardView);
        groceryCardView = view2.findViewById(R.id.groceryCardView);
        staffCardView = view2.findViewById(R.id.staffCardView);
        maintenanceCardView = view2.findViewById(R.id.maintenanceCardView);
        marketingCardView = view2.findViewById(R.id.marketingCardView);
        taxesAmcCardView = view2.findViewById(R.id.taxesAmcCardView);

        firstExpenseCardView = view2.findViewById(R.id.firstExpense);
        secondExpenseCardView = view2.findViewById(R.id.secondExpense);
        thirdExpenseCardView = view2.findViewById(R.id.thirdExpense);
        fourthExpenseCardView = view2.findViewById(R.id.fourthExpense);
        fifthExpenseCardView = view2.findViewById(R.id.fifthExpense);

        paidByNameTextView1 = view2.findViewById(R.id.tenantNameTextView1);
        paidByNameTextView2 = view2.findViewById(R.id.tenantNameTextView2);
        paidByNameTextView3 = view2.findViewById(R.id.tenantNameTextView3);
        paidByNameTextView4 = view2.findViewById(R.id.tenantNameTextView4);
        paidByNameTextView5 = view2.findViewById(R.id.tenantNameTextView5);

        dateTextView1 = view2.findViewById(R.id.dateTextView1);
        dateTextView2 = view2.findViewById(R.id.dateTextView2);
        dateTextView3 = view2.findViewById(R.id.dateTextView3);
        dateTextView4 = view2.findViewById(R.id.dateTextView4);
        dateTextView5 = view2.findViewById(R.id.dateTextView5);

        categoryTextView1 = view2.findViewById(R.id.categoryTextView1);
        categoryTextView2 = view2.findViewById(R.id.categoryTextView2);
        categoryTextView3 = view2.findViewById(R.id.categoryTextView3);
        categoryTextView4 = view2.findViewById(R.id.categoryTextView4);
        categoryTextView5 = view2.findViewById(R.id.categoryTextView5);

        amountTextView1 = view2.findViewById(R.id.amountTextView1);
        amountTextView2 = view2.findViewById(R.id.amountTextView2);
        amountTextView3 = view2.findViewById(R.id.amountTextView3);
        amountTextView4 = view2.findViewById(R.id.amountTextView4);
        amountTextView5 = view2.findViewById(R.id.amountTextView5);

        paidToTextView1 = view2.findViewById(R.id.roomNumberTenantTextView1);
        paidToTextView2 = view2.findViewById(R.id.roomNumberTenantTextView2);
        paidToTextView3 = view2.findViewById(R.id.roomNumberTenantTextView3);
        paidToTextView4 = view2.findViewById(R.id.roomNumberTenantTextView4);
        paidToTextView5 = view2.findViewById(R.id.roomNumberTenantTextView5);

        descriptionTetView1 = view2.findViewById(R.id.descriptionTextView1);
        descriptionTetView2 = view2.findViewById(R.id.descriptionTextView2);
        descriptionTetView3 = view2.findViewById(R.id.descriptionTextView3);
        descriptionTetView4 = view2.findViewById(R.id.descriptionTextView4);
        descriptionTetView5 = view2.findViewById(R.id.descriptionTextView5);

        inflater = getLayoutInflater();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        DatabaseReference databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Cashflow/");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CashflowDetails cashflowDetails = snapshot.getValue(CashflowDetails.class);
                    if(!cashflowDetails.inout){
                        expensesDetailsList.add(cashflowDetails);
                    }
                }

                int size = expensesDetailsList.size();

                Collections.sort(expensesDetailsList, Collections.<CashflowDetails>reverseOrder());

                switch(size){
                    case 0:
                        firstExpenseCardView.setVisibility(View.GONE);
                        secondExpenseCardView.setVisibility(View.GONE);
                        thirdExpenseCardView.setVisibility(View.GONE);
                        fourthExpenseCardView.setVisibility(View.GONE);
                        fifthExpenseCardView.setVisibility(View.GONE);

                        break;

                    case 1:
                        secondExpenseCardView.setVisibility(View.GONE);
                        thirdExpenseCardView.setVisibility(View.GONE);
                        fourthExpenseCardView.setVisibility(View.GONE);
                        fifthExpenseCardView.setVisibility(View.GONE);

                        paidByNameTextView1.setText(expensesDetailsList.get(0).paidBy);
                        dateTextView1.setText(expensesDetailsList.get(0).date);
                        categoryTextView1.setText(expensesDetailsList.get(0).category);
                        amountTextView1.setText(expensesDetailsList.get(0).amount);
                        descriptionTetView1.setText(expensesDetailsList.get(0).description);
                        paidToTextView1.setText(expensesDetailsList.get(0).paidTo);

                        break;

                    case 2:
                        thirdExpenseCardView.setVisibility(View.GONE);
                        fourthExpenseCardView.setVisibility(View.GONE);
                        fifthExpenseCardView.setVisibility(View.GONE);

                        paidByNameTextView1.setText(expensesDetailsList.get(0).paidBy);
                        dateTextView1.setText(expensesDetailsList.get(0).date);
                        categoryTextView1.setText(expensesDetailsList.get(0).category);
                        amountTextView1.setText(expensesDetailsList.get(0).amount);
                        descriptionTetView1.setText(expensesDetailsList.get(0).description);
                        paidToTextView1.setText(expensesDetailsList.get(0).paidTo);

                        paidByNameTextView2.setText(expensesDetailsList.get(1).paidBy);
                        dateTextView2.setText(expensesDetailsList.get(1).date);
                        categoryTextView2.setText(expensesDetailsList.get(1).category);
                        amountTextView2.setText(expensesDetailsList.get(1).amount);
                        descriptionTetView2.setText(expensesDetailsList.get(1).description);
                        paidToTextView2.setText(expensesDetailsList.get(1).paidTo);

                        break;

                    case 3:
                        fourthExpenseCardView.setVisibility(View.GONE);
                        fifthExpenseCardView.setVisibility(View.GONE);

                        paidByNameTextView1.setText(expensesDetailsList.get(0).paidBy);
                        dateTextView1.setText(expensesDetailsList.get(0).date);
                        categoryTextView1.setText(expensesDetailsList.get(0).category);
                        amountTextView1.setText(expensesDetailsList.get(0).amount);
                        descriptionTetView1.setText(expensesDetailsList.get(0).description);
                        paidToTextView1.setText(expensesDetailsList.get(0).paidTo);

                        paidByNameTextView2.setText(expensesDetailsList.get(1).paidBy);
                        dateTextView2.setText(expensesDetailsList.get(1).date);
                        categoryTextView2.setText(expensesDetailsList.get(1).category);
                        amountTextView2.setText(expensesDetailsList.get(1).amount);
                        descriptionTetView2.setText(expensesDetailsList.get(1).description);
                        paidToTextView2.setText(expensesDetailsList.get(1).paidTo);

                        paidByNameTextView3.setText(expensesDetailsList.get(2).paidBy);
                        dateTextView3.setText(expensesDetailsList.get(2).date);
                        categoryTextView3.setText(expensesDetailsList.get(2).category);
                        amountTextView3.setText(expensesDetailsList.get(2).amount);
                        descriptionTetView3.setText(expensesDetailsList.get(2).description);
                        paidToTextView3.setText(expensesDetailsList.get(2).paidTo);

                        break;

                    case 4:
                        fifthExpenseCardView.setVisibility(View.GONE);

                        paidByNameTextView1.setText(expensesDetailsList.get(0).paidBy);
                        dateTextView1.setText(expensesDetailsList.get(0).date);
                        categoryTextView1.setText(expensesDetailsList.get(0).category);
                        amountTextView1.setText(expensesDetailsList.get(0).amount);
                        descriptionTetView1.setText(expensesDetailsList.get(0).description);
                        paidToTextView1.setText(expensesDetailsList.get(0).paidTo);

                        paidByNameTextView2.setText(expensesDetailsList.get(1).paidBy);
                        dateTextView2.setText(expensesDetailsList.get(1).date);
                        categoryTextView2.setText(expensesDetailsList.get(1).category);
                        amountTextView2.setText(expensesDetailsList.get(1).amount);
                        descriptionTetView2.setText(expensesDetailsList.get(1).description);
                        paidToTextView2.setText(expensesDetailsList.get(1).paidTo);

                        paidByNameTextView3.setText(expensesDetailsList.get(2).paidBy);
                        dateTextView3.setText(expensesDetailsList.get(2).date);
                        categoryTextView3.setText(expensesDetailsList.get(2).category);
                        amountTextView3.setText(expensesDetailsList.get(2).amount);
                        descriptionTetView3.setText(expensesDetailsList.get(2).description);
                        paidToTextView3.setText(expensesDetailsList.get(2).paidTo);

                        paidByNameTextView4.setText(expensesDetailsList.get(3).paidBy);
                        dateTextView4.setText(expensesDetailsList.get(3).date);
                        categoryTextView4.setText(expensesDetailsList.get(3).category);
                        amountTextView4.setText(expensesDetailsList.get(3).amount);
                        descriptionTetView4.setText(expensesDetailsList.get(3).description);
                        paidToTextView4.setText(expensesDetailsList.get(3).paidTo);

                        break;

                    default:
                        paidByNameTextView1.setText(expensesDetailsList.get(0).paidBy);
                        dateTextView1.setText(expensesDetailsList.get(0).date);
                        categoryTextView1.setText(expensesDetailsList.get(0).category);
                        amountTextView1.setText(expensesDetailsList.get(0).amount);
                        descriptionTetView1.setText(expensesDetailsList.get(0).description);
                        paidToTextView1.setText(expensesDetailsList.get(0).paidTo);

                        paidByNameTextView2.setText(expensesDetailsList.get(1).paidBy);
                        dateTextView2.setText(expensesDetailsList.get(1).date);
                        categoryTextView2.setText(expensesDetailsList.get(1).category);
                        amountTextView2.setText(expensesDetailsList.get(1).amount);
                        descriptionTetView2.setText(expensesDetailsList.get(1).description);
                        paidToTextView2.setText(expensesDetailsList.get(1).paidTo);

                        paidByNameTextView3.setText(expensesDetailsList.get(2).paidBy);
                        dateTextView3.setText(expensesDetailsList.get(2).date);
                        categoryTextView3.setText(expensesDetailsList.get(2).category);
                        amountTextView3.setText(expensesDetailsList.get(2).amount);
                        descriptionTetView3.setText(expensesDetailsList.get(2).description);
                        paidToTextView3.setText(expensesDetailsList.get(2).paidTo);

                        paidByNameTextView4.setText(expensesDetailsList.get(3).paidBy);
                        dateTextView4.setText(expensesDetailsList.get(3).date);
                        categoryTextView4.setText(expensesDetailsList.get(3).category);
                        amountTextView4.setText(expensesDetailsList.get(3).amount);
                        descriptionTetView4.setText(expensesDetailsList.get(3).description);
                        paidToTextView4.setText(expensesDetailsList.get(3).paidTo);

                        paidByNameTextView5.setText(expensesDetailsList.get(4).paidBy);
                        dateTextView5.setText(expensesDetailsList.get(4).date);
                        categoryTextView5.setText(expensesDetailsList.get(4).category);
                        amountTextView5.setText(expensesDetailsList.get(4).amount);
                        descriptionTetView5.setText(expensesDetailsList.get(4).description);
                        paidToTextView5.setText(expensesDetailsList.get(4).paidTo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final LayoutInflater finalInflater = inflater;
        servicesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = getLayoutInflater().inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
                description = viewDialog.findViewById(R.id.descriptionEditText);
                paidBy = viewDialog.findViewById(R.id.paidByEditText);
                paidTo = viewDialog.findViewById(R.id.paidToEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Add Expense Details")
                        .setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", "Saving...", true);
                        String amountString = amount.getText().toString();
                        String categoryString = "Services";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());
                        String uidExpense = databaseReference.push().getKey();

                        if(amountString.isEmpty()){
                            Toast.makeText(getContext(), "Amount Required!", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });

        final LayoutInflater finalInflater1 = inflater;
        groceryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = getLayoutInflater().inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
                description = viewDialog.findViewById(R.id.descriptionEditText);
                paidBy = viewDialog.findViewById(R.id.paidByEditText);
                paidTo = viewDialog.findViewById(R.id.paidToEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Add Expense Details")
                        .setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", "Saving...", true);

                        String amountString = amount.getText().toString();
                        String categoryString = "Grocery";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());
                        String uidExpense = databaseReference.push().getKey();

                        if(amountString.isEmpty()){
                            Toast.makeText(getContext(), "Amount Required!", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });

        final LayoutInflater finalInflater2 = inflater;
        staffCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = getLayoutInflater().inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
                description = viewDialog.findViewById(R.id.descriptionEditText);
                paidBy = viewDialog.findViewById(R.id.paidByEditText);
                paidTo = viewDialog.findViewById(R.id.paidToEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Add Expense Details")
                        .setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", "Saving...", true);

                        String amountString = amount.getText().toString();
                        String categoryString = "Staff";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());
                        String uidExpense = databaseReference.push().getKey();

                        if(amountString.isEmpty()){
                            Toast.makeText(getContext(), "Amount Required!", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
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
                final View viewDialog = getLayoutInflater().inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
                description = viewDialog.findViewById(R.id.descriptionEditText);
                paidBy = viewDialog.findViewById(R.id.paidByEditText);
                paidTo = viewDialog.findViewById(R.id.paidToEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Add Expense Details")
                        .setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", "Saving...", true);

                        String amountString = amount.getText().toString();
                        String categoryString = "Maintenance";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());
                        String uidExpense = databaseReference.push().getKey();

                        if(amountString.isEmpty()){
                            Toast.makeText(getContext(), "Amount Required!", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
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
                final View viewDialog = getLayoutInflater().inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
                description = viewDialog.findViewById(R.id.descriptionEditText);
                paidBy = viewDialog.findViewById(R.id.paidByEditText);
                paidTo = viewDialog.findViewById(R.id.paidToEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Add Expense Details")
                        .setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", "Saving...", true);

                        String amountString = amount.getText().toString();
                        String categoryString = "Marketing";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();


                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());
                        String uidExpense = databaseReference.push().getKey();

                        if(amountString.isEmpty()){
                            Toast.makeText(getContext(), "Amount Required!", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
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
                final View viewDialog = getLayoutInflater().inflate(R.layout.dialog_expense, null);

                amount = viewDialog.findViewById(R.id.amountEditText);
                description = viewDialog.findViewById(R.id.descriptionEditText);
                paidBy = viewDialog.findViewById(R.id.paidByEditText);
                paidTo = viewDialog.findViewById(R.id.paidToEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Add Expense Details")
                        .setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", "Saving...", true);

                        String amountString = amount.getText().toString();
                        String categoryString = "Taxes and AMC";
                        String descriptionString = description.getText().toString();
                        String paidByString = paidBy.getText().toString();
                        String paidToString = paidTo.getText().toString();
                        String uidExpense = databaseReference.push().getKey();

                        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                        if(amountString.isEmpty()){
                            Toast.makeText(getContext(), "Amount Required!", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
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




        return view2;
    }
}
