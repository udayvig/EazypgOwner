package com.example.EazyPG.owner.Activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.EazyPG.owner.DetailList.TenantDetailList;
import com.example.EazyPG.owner.DetailsClasses.TenantDetails;
import com.example.ainesh.eazypg_owner.R;
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
import com.katepratik.msg91api.MSG91;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TenantActivity extends AppCompatActivity {


    ListView listView;
    View emptyList;
    List<TenantDetails> tenantDetailsList;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    ImageView addTenant, qrImage;
    EditText name, phone, room, rentAmount;
    TextView dateOfJoining;

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

        listView = findViewById(R.id.listViewTenant);
        emptyList = findViewById(R.id.emptyListTenant);
        listView.setEmptyView(emptyList);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = firebaseDatabase.getReference();

        inflater = getLayoutInflater();

        listView = findViewById(R.id.listViewTenant);
        addTenant = findViewById(R.id.addTenant);
        view = findViewById(R.id.tenantLayout);

        name = findViewById(R.id.tenantNameEditText);
        phone = findViewById(R.id.tenantPhoneEditText);
        room = findViewById(R.id.tenantRoomEditText);
        dateOfJoining = findViewById(R.id.tenantDateEditText);
        rentAmount = findViewById(R.id.tenantRentEditText);

        snackbar = Snackbar.make(view, "Tap item to view", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(TenantActivity.this, R.color.DarkGreen));
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
                final Calendar calendar = Calendar.getInstance();

                final View viewDialog = inflater.inflate(R.layout.dialog_tenant, null);
                final TextView tenantCustomTitle;
                name = viewDialog.findViewById(R.id.tenantNameEditText);
                phone = viewDialog.findViewById(R.id.tenantPhoneEditText);
                room = viewDialog.findViewById(R.id.tenantRoomEditText);
                dateOfJoining = viewDialog.findViewById(R.id.tenantDateEditText);
                rentAmount = viewDialog.findViewById(R.id.tenantRentEditText);

                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(Calendar.YEAR, i);
                        calendar.set(Calendar.MONTH, i1);
                        calendar.set(Calendar.DAY_OF_MONTH, i2);

                        String myFormat = "dd/MM/yy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

                        dateOfJoining.setText(sdf.format(calendar.getTime()));
                    }
                };

                dateOfJoining.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new DatePickerDialog(TenantActivity.this, date, calendar
                                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                final View addTitleView = inflater.inflate(R.layout.custom_title4, null);
                tenantCustomTitle = addTitleView.findViewById(R.id.tenantCustomTitle);

                AlertDialog.Builder builder = new AlertDialog.Builder(TenantActivity.this);
                builder.setCustomTitle(tenantCustomTitle);

                builder.setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        MSG91 msg91 = new MSG91("163776AiifTBEVMZl5aae0bce");
                        msg91.composeMessage("EazyPG", "Hello " + name.getText().toString() + ". App download karlo: ");
                        msg91.to(phone.getText().toString());
                        String sendStatus = msg91.send();

                        Log.i("MyMSGStatus", sendStatus);

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

                            final AlertDialog.Builder builder1 = new AlertDialog.Builder(TenantActivity.this);
                            builder1.setTitle("Scan to connect");
                            builder1.setMessage("This QR Code is shown only once.");
                            builder1.setView(viewDialog);
                            builder1.setCancelable(false);
                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
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
