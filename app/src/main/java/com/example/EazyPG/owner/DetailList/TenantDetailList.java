package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EazyPG.owner.Activities.BillActivity;
import com.example.EazyPG.owner.Activities.FineActivity;
import com.example.EazyPG.owner.Activities.PaymentActivity;
import com.example.EazyPG.owner.Activities.TenantDashboardProfile;
import com.example.EazyPG.owner.Activities.TenantDocumentsActivity;
import com.example.EazyPG.owner.DetailsClasses.TenantDetails;
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

public class TenantDetailList extends ArrayAdapter<TenantDetails> {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private Activity context;
    private List<TenantDetails> tenantList;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE2";
    public static final String EXTRA_MESSAGE3 = "com.example.myfirstapp.MESSAGE3";
    public String id;
    private FloatingActionButton callButton, paymentButton;
    private Button deleteTenant, fineButton, billButton;

    public TenantDetailList(Activity context, List<TenantDetails> tenantList) {
        super(context, R.layout.tenant_row, tenantList);

        this.context = context;
        this.tenantList = tenantList;
    }

    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = context.getLayoutInflater();

        View listViewItemTenant = inflater.inflate(R.layout.tenant_row, null, true);

        callButton = listViewItemTenant.findViewById(R.id.callButton);
        paymentButton = listViewItemTenant.findViewById(R.id.paymentButton);
        deleteTenant = listViewItemTenant.findViewById(R.id.deleteTenant);
        fineButton = listViewItemTenant.findViewById(R.id.fineButton);
        billButton = listViewItemTenant.findViewById(R.id.billButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final TenantDetails tenantDetails = tenantList.get(position);

        databaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Tenants/CurrentTenants");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ids.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    TenantDetails tenantDetails1 = snapshot.getValue(TenantDetails.class);
                    id = tenantDetails1.id;
                    ids.add(id);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewItemTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TenantDocumentsActivity.class);
                intent.putExtra(EXTRA_MESSAGE3, ids.get(position));
                context.startActivity(intent);

            }
        });

        TextView first = listViewItemTenant.findViewById(R.id.firstTextView);
        TextView second = listViewItemTenant.findViewById(R.id.secondTextView);
        final TextView third = listViewItemTenant.findViewById(R.id.thirdTextView);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PaymentActivity.class);
                intent.putExtra(EXTRA_MESSAGE, ids.get(position));
                context.startActivity(intent);

            }
        });


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + third.getText().toString()));
                        context.startActivity(callIntent);

                }
                catch (ActivityNotFoundException activityException) {
                    Toast.makeText(context, "Call failed!", Toast.LENGTH_SHORT).show();
                }
                catch (SecurityException e) {
                    Toast.makeText(context, "Call failed!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        fineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(dialogFine);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String fine = fineEditText.getText().toString();
                        String fineId = databaseReference.push().getKey();

                        databaseReference = firebaseDatabase.getReference("Tenants/" + tenantDetails.id + "/");
                        databaseReference.child("Accounts").child("Fine").child(fineId).setValue(fine);
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();*/
                Intent intent = new Intent(context, FineActivity.class);
                intent.putExtra(EXTRA_MESSAGE, tenantDetails.id);
                intent.putExtra(EXTRA_MESSAGE2, tenantDetails.room);
                context.startActivity(intent);
            }
        });

        billButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(dialogBill);
                builder.setTitle("Enter Bill");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        billAmount = billAmountEditText.getText().toString();
                        billCategory = billCategoryEditText.getText().toString();

                        Toast.makeText(context,billAmount + " " + billCategory, Toast.LENGTH_SHORT).show();

                        String billId = databaseReference.push().getKey();
                        BillDetails billDetails = new BillDetails(billId, billCategory, billAmount, false);

                        databaseReference = firebaseDatabase.getReference("Tenants/" + tenantDetails.id + "/");
                        databaseReference.child("Accounts").child("Bills").child(billId).setValue(billDetails);
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();*/
                Intent intent = new Intent(context, BillActivity.class);
                intent.putExtra(EXTRA_MESSAGE, tenantDetails.id);
                intent.putExtra(EXTRA_MESSAGE2, tenantDetails.room);
                context.startActivity(intent);
            }
        });

        deleteTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference fromReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Tenants/CurrentTenants/");
                final DatabaseReference toReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Tenants/PreviousTenants/");
                final DatabaseReference toReferenceRoom = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + tenantDetails.getRoom() + "/Tenant/PreviousTenants");

                final DatabaseReference roomReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + tenantDetails.getRoom() + "/Tenant/CurrentTenants");
                roomReference.child(ids.get(position)).setValue(null);

                fromReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        toReference.child(ids.get(position)).setValue(dataSnapshot.child(ids.get(position)).getValue());
                        toReferenceRoom.child(ids.get(position)).setValue(dataSnapshot.child(ids.get(position)).getValue());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        fromReference.child(ids.get(position)).setValue(null);
                    }
                });
                thread.start();

            }
        });

        first.setText(tenantDetails.getName());
        second.setText(tenantDetails.getRoom());
        third.setText(tenantDetails.getPhone());

        return listViewItemTenant;

    }
}
