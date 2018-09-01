package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.EazyPG.owner.DetailList.TenantDetailList;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FineActivity extends AppCompatActivity {

    EditText fineAmountEditText;
    Button fineOkButton;

    String fineAmount;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);

        Intent intent = getIntent();
        final String tenantId = intent.getStringExtra(TenantDetailList.EXTRA_MESSAGE);
        final String tenantRoom = intent.getStringExtra(TenantDetailList.EXTRA_MESSAGE2);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        fineAmountEditText = findViewById(R.id.fineEditText);

        fineOkButton = findViewById(R.id.fineOkButton);

        fineOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fineAmount = fineAmountEditText.getText().toString();

                Toast.makeText(FineActivity.this, fineAmount + " ", Toast.LENGTH_SHORT).show();

                databaseReference = firebaseDatabase.getReference("Tenants/" + tenantId + "/");
                String fineId = databaseReference.push().getKey();
                FineDetails fineDetails = new FineDetails(fineId, fineAmount, false);

                databaseReference.child("Accounts").child("Fines").child(fineId).setValue(fineDetails);

                DatabaseReference databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Tenants/Current Tenants/" + tenantId);
                databaseReference1.child("Accounts").child("Fines").child(fineId).setValue(fineDetails);

                DatabaseReference databaseReference2 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Rooms/" + tenantRoom + "/Tenant/CurrentTenants");
                databaseReference2.child("Accounts").child("Fines").child(fineId).setValue(fineDetails);
            }
        });
    }
}
