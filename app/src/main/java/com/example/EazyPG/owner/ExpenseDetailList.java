package com.example.EazyPG.owner;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDetailList extends ArrayAdapter<ExpenseDetails> {

    private Activity context;
    private List<ExpenseDetails> expenseList;

    public ExpenseDetailList(Activity context, List<ExpenseDetails> expenseList){
        super(context, R.layout.expense_row, expenseList);

        this.context = context;
        this.expenseList = expenseList;
    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Expenses/");
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemExpense = inflater.inflate(R.layout.expense_row, null, true);
        final ExpenseDetails expenseDetails = expenseList.get(position);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ids.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ExpenseDetails expenseDetails1 = snapshot.getValue(ExpenseDetails.class);
                    String id = expenseDetails1.expenseId;
                    ids.add(id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        TextView first = listViewItemExpense.findViewById(R.id.firstTextView);
        TextView second = listViewItemExpense.findViewById(R.id.secondTextView);
        TextView third = listViewItemExpense.findViewById(R.id.thirdTextView);
        TextView fourth = listViewItemExpense.findViewById(R.id.fourthTextView);
        TextView fifth = listViewItemExpense.findViewById(R.id.fifthTextView);
        TextView sixth = listViewItemExpense.findViewById(R.id.sixthTextView);

        first.setText(expenseDetails.getAmount());
        second.setText(expenseDetails.getDate());
        third.setText(expenseDetails.getCategory());
        fourth.setText(expenseDetails.getDescription());
        fifth.setText(expenseDetails.getPaidBy());
        sixth.setText(expenseDetails.getPaidTo());

        return listViewItemExpense;
    }
}
