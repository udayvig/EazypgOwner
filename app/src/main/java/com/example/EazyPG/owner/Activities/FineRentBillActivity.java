package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FineRentBillActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    DatabaseReference databaseReference;

    EditText fineAmountEditText;

    String fineAmount;

    Button fineOkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine_rent_bill);

        Intent intent = getIntent();
        final String tenantId = intent.getStringExtra(RentCollectionPaidDetailList.EXTRA_MESSAGE);
        final String tenantRoom = intent.getStringExtra(RentCollectionPaidDetailList.EXTRA_MESSAGE2);

        fineAmountEditText = findViewById(R.id.fineEditText);

        fineOkButton = findViewById(R.id.fineOkButton);

        fineOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fineAmount = fineAmountEditText.getText().toString();

                databaseReference = firebaseDatabase.getReference("Tenants/" + tenantId + "/");
                String fineId = databaseReference.push().getKey();
                FineDetails fineDetails = new FineDetails(fineId, fineAmount, false);

                final DatabaseReference databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Rooms/" + tenantRoom + "/Tenant/CurrentTenants/" + tenantId);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String prevFine = dataSnapshot.child("Fine").getValue(String.class);

                        String fine = fineAmount;

                        if(prevFine != null){
                            fine = Integer.toString(Integer.parseInt(prevFine) + Integer.parseInt(fineAmount));
                        }

                        databaseReference.child("Fine").setValue(fine);

                        DatabaseReference databaseReference2 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Tenants/CurrentTenants/" + tenantId);
                        databaseReference2.child("Fine").setValue(fine);

                        DatabaseReference databaseReference3 = firebaseDatabase.getReference("Tenants/" + tenantId);
                        databaseReference3.child("fine").setValue(fine);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                DatabaseReference databaseReference4 = firebaseDatabase.getReference("Tenants/" + tenantId);

                databaseReference4.child("Accounts").child("Fines").child(fineId).setValue(fineDetails);
            }
        });
    }
}
