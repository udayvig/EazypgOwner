package com.example.EazyPG.owner.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.EazyPG.owner.StaffDetailList;
import com.example.EazyPG.owner.StaffDetails;
import com.example.EazyPG.owner.TenantDetailList;
import com.example.EazyPG.owner.TenantDetails;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.ArrayList;
import java.util.List;

public class TenantActivity extends AppCompatActivity {


    ListView listView;
    List<TenantDetails> tenantDetailsList;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    ImageView addTenant, qrImage;
    EditText name, phone, room, dateOfJoining, rentAmount;

    Snackbar snackbar;
    View view;

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);

        Toolbar toolbar = findViewById(R.id.tenantToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = firebaseDatabase.getReference();

        inflater = getLayoutInflater();

        listView = findViewById(R.id.listView);
        addTenant = findViewById(R.id.addTenant);
        view = findViewById(R.id.tenantLayout);

        name = findViewById(R.id.tenantNameEditText);
        phone = findViewById(R.id.tenantPhoneEditText);
        room = findViewById(R.id.tenantRoomEditText);
        dateOfJoining = findViewById(R.id.tenantDateEditText);
        rentAmount = findViewById(R.id.tenantRentEditText);

        snackbar = Snackbar.make(view, "Tap item to view", Snackbar.LENGTH_LONG);
        snackbar.show();

        tenantDetailsList = new ArrayList<>();

        databaseReference = firebaseDatabase.getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Tenants/");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tenantDetailsList.clear();

                for (DataSnapshot dataSnapshotTenant : dataSnapshot.getChildren()) {

                    TenantDetails tenantDetails = dataSnapshotTenant.getValue(TenantDetails.class);
                    tenantDetailsList.add(tenantDetails);
                }

                TenantDetailList adapter = new TenantDetailList(TenantActivity.this, tenantDetailsList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View viewDialog = inflater.inflate(R.layout.dialog_tenant, null);

                name = viewDialog.findViewById(R.id.tenantNameEditText);
                phone = viewDialog.findViewById(R.id.tenantPhoneEditText);
                room = viewDialog.findViewById(R.id.tenantRoomEditText);
                dateOfJoining = viewDialog.findViewById(R.id.tenantDateEditText);
                rentAmount = viewDialog.findViewById(R.id.tenantRentEditText);

                final AlertDialog.Builder builder = new AlertDialog.Builder(TenantActivity.this);

                builder.setTitle("Add Tenant Details");

                builder.setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final View viewDialog = inflater.inflate(R.layout.dialog_qr, null);
                        qrImage = viewDialog.findViewById(R.id.qrImageView);

                        QRCodeWriter writer = new QRCodeWriter();
                        try {

                            String content = FirebaseAuth.getInstance().getCurrentUser().getUid() + " " +
                                    name.getText().toString() + " " + phone.getText().toString() + " " +
                                    room.getText().toString() + " " + dateOfJoining.getText().toString() + " " +
                                    rentAmount.getText().toString();

                            BitMatrix bitMatrix = writer.encode(content , BarcodeFormat.QR_CODE, 512, 512);
                            int width = bitMatrix.getWidth();
                            int height = bitMatrix.getHeight();
                            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                            for (int x = 0; x < width; x++) {
                                for (int y = 0; y < height; y++) {
                                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                                }
                            }
                            qrImage.setImageBitmap(bmp);

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(TenantActivity.this);
                            builder1.setTitle("Scan to connect");
                            builder1.setMessage("This QR Code is shown only once.");
                            builder1.setView(viewDialog);
                            builder1.setCancelable(false);
                            builder1.show();

                        } catch (WriterException e) {
                            e.printStackTrace();
                        }
                    }
                });

                builder.setNegativeButton("Cancel", null);
                builder.show();

            }
        });
    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(TenantActivity.this, HomePageActivity.class));
        finish();
    }

}
