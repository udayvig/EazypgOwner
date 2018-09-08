package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FineRentBillActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    DatabaseReference databaseReference;

    EditText fineAmountEditText;

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
                String fineAmount = fineAmountEditText.getText().toString();

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String dateStr = dateFormat.format(date);

                String dateString = dateStr.substring(6,10) + "-" + dateStr.substring(3,5);

                databaseReference = firebaseDatabase.getReference("Tenants/" + tenantId + "/");
                String fineId = databaseReference.push().getKey();
                FineDetails fineDetails = new FineDetails(fineId, fineAmount, false);

                databaseReference.child("Accounts").child("Fines").child(fineId).setValue(fineDetails);

                DatabaseReference databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Tenants/CurrentTenants/" + tenantId);
                databaseReference1.child("Accounts").child("Fines").child(fineId).setValue(fineDetails);

                DatabaseReference databaseReference2 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Rooms/" + tenantRoom + "/Tenant/CurrentTenants/" + tenantId);
                databaseReference2.child("Accounts").child("Fines").child(fineId).setValue(fineDetails);
            }
        });
    }
}
