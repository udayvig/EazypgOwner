package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.EazyPG.owner.DetailsClasses.CashflowDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PassbookDetailList extends ArrayAdapter<CashflowDetails> {

    private Activity context;
    private List<CashflowDetails> passbookList;

    public PassbookDetailList(Activity context, List<CashflowDetails> passbookList){
        super(context, R.layout.passbook_row, passbookList);

        this.context = context;
        this.passbookList = passbookList;
    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Cashflow/");
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemPassbook = inflater.inflate(R.layout.passbook_row, null, true);

        final CashflowDetails passbookDetails = passbookList.get(position);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ids.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CashflowDetails cashflowDetails1 = snapshot.getValue(CashflowDetails.class);
                    String id = cashflowDetails1.expenseId;
                    ids.add(id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*listViewItemPassbook.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Delete Item");
                builder.setMessage("Are you sure you want to delete item?");
                builder.setIcon(R.drawable.ic_warning_black_24dp);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConnectivityManager connectivityManager
                                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                            final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Deleting...", true);

                            String id = ids.get(position);

                            databaseReference.child(id).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(context, "Check your internet connection.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Cancel", null);
                builder.show();

                return true;
            }
        });*/

        TextView first = listViewItemPassbook.findViewById(R.id.firstTextView);
        TextView second = listViewItemPassbook.findViewById(R.id.secondTextView);
        TextView third = listViewItemPassbook.findViewById(R.id.thirdTextView);
        TextView fourth = listViewItemPassbook.findViewById(R.id.fourthTextView);
        TextView fifth = listViewItemPassbook.findViewById(R.id.fifthTextView);
        TextView sixth;

        first.setText(passbookDetails.getAmount());
        second.setText(passbookDetails.getDate());
        third.setText(passbookDetails.getCategory());
        fourth.setText(passbookDetails.getDescription());
        fifth.setText(passbookDetails.getPaidBy());

        if(passbookDetails.inout){
            sixth = listViewItemPassbook.findViewById(R.id.sixthTextView);
            sixth.setVisibility(View.GONE);
            ImageView iv = listViewItemPassbook.findViewById(R.id.inOrOut);
            iv.setImageResource(R.drawable.ic_arrow_downward_black_24dp);
        }else{
            sixth = listViewItemPassbook.findViewById(R.id.sixthTextView);
            sixth.setText(passbookDetails.getPaidTo());
            ImageView iv = listViewItemPassbook.findViewById(R.id.inOrOut);
            iv.setImageResource(R.drawable.ic_arrow_upward_black_24dp);
        }

        return listViewItemPassbook;
    }
}
