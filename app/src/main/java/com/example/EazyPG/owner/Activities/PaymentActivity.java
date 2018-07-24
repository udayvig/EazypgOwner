package com.example.EazyPG.owner.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.EazyPG.owner.DetailList.PaymentDetailList;
import com.example.EazyPG.owner.DetailsClasses.PaymentDetails;
import com.example.EazyPG.owner.DetailsClasses.StaffDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    ListView listView;
    List<String> paymentList;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    TextView addPaymentTitle;

    Snackbar snackbar;
    View view;
    View emptyList;

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Toolbar toolbar = findViewById(R.id.staffToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

        inflater = getLayoutInflater();

        listView = findViewById(R.id.listView);
        emptyList = findViewById(R.id.emptyList);
        listView.setEmptyView(emptyList);

        view = findViewById(R.id.paymentLayout);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                paymentList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String id = snapshot.child("Tenants").getValue(String.class);
                    paymentList.add(id);

                }

                PaymentDetailList adapter = new PaymentDetailList(PaymentActivity.this, paymentList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
