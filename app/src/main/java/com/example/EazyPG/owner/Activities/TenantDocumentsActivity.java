package com.example.EazyPG.owner.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.EazyPG.owner.DetailList.TenantDetailList;
import com.example.ainesh.eazypg_owner.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class TenantDocumentsActivity extends AppCompatActivity {

    CardView aadharFrontCardView, aadharBackCardView, collegeIDFrontCardView, collegeIDBackCardView;

    ImageView aadharFrontImageView, aadharBackImageView, collegeIdFrontImageView, collegeIdBackImageView;

    Button downloadButton;

    StorageReference storageReference;
    FirebaseStorage storage;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    String id;

    boolean loaded;

    String selfieId, aadharBackId, aadharFrontId, collegeIdBackId, collegeIdFrontId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_documents);

        Intent intent = getIntent();
        id = intent.getStringExtra(TenantDetailList.EXTRA_MESSAGE3);

        aadharFrontCardView = findViewById(R.id.aadharFrontCardView);
        aadharBackCardView = findViewById(R.id.aadharbackCardView);
        collegeIDFrontCardView = findViewById(R.id.collegeIDFrontCardView);
        collegeIDBackCardView = findViewById(R.id.collegeIDBackCardView);

        aadharFrontImageView = findViewById(R.id.aadharFrontImageView);
        aadharBackImageView = findViewById(R.id.aadharBackImageView);
        collegeIdFrontImageView = findViewById(R.id.collegeIdFrontImageView);
        collegeIdBackImageView = findViewById(R.id.collegeIdBackImageView);

        downloadButton = findViewById(R.id.downloadButton);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReferenceFromUrl("gs://eazypgowner.appspot.com").child("Documents");

        final long ONE_MEGABYTE = 1024 * 1024;

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Tenants/" + id + "/My Docs/");

        final ProgressDialog progressDialog = ProgressDialog.show(TenantDocumentsActivity.this, "Loading", "Please wait while documents are downloading..", true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                selfieId = dataSnapshot.child("Selfie").getValue(String.class);
                aadharBackId = dataSnapshot.child("Aadhar Back").getValue(String.class);
                aadharFrontId = dataSnapshot.child("Aadhar Front").getValue(String.class);
                collegeIdBackId = dataSnapshot.child("CollegeID Back").getValue(String.class);
                collegeIdFrontId = dataSnapshot.child("CollegeID Front").getValue(String.class);

                storageReference.child(id).child(aadharFrontId).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        aadharFrontImageView.setImageBitmap(bitmap);
                    }
                });

                storageReference.child(id).child(aadharBackId).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        aadharBackImageView.setImageBitmap(bitmap);
                    }
                });

                storageReference.child(id).child(collegeIdBackId).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        collegeIdBackImageView.setImageBitmap(bitmap);
                    }
                });

                storageReference.child(id).child(collegeIdFrontId).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        collegeIdFrontImageView.setImageBitmap(bitmap);

                        progressDialog.dismiss();

                        loaded = true;


                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        aadharFrontCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loaded) {

                    final Dialog builder = new Dialog(TenantDocumentsActivity.this);
                    builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    builder.getWindow().setBackgroundDrawable(
                            new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    ImageView imageView = new ImageView(getApplicationContext());

                    aadharFrontImageView.buildDrawingCache(true);

                    Bitmap bitmap = Bitmap.createBitmap(aadharFrontImageView.getDrawingCache(true));
                    imageView.setImageBitmap(bitmap);
                    builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                            1000,
                            1000));
                    builder.setCancelable(true);

                    builder.show();

                }

            }
        });

        aadharBackCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loaded) {

                    final Dialog builder = new Dialog(TenantDocumentsActivity.this);
                    builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    builder.getWindow().setBackgroundDrawable(
                            new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    ImageView imageView = new ImageView(getApplicationContext());

                    aadharBackImageView.buildDrawingCache(true);

                    Bitmap bitmap = Bitmap.createBitmap(aadharBackImageView.getDrawingCache(true));
                    imageView.setImageBitmap(bitmap);
                    builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                            1000,
                            1000));
                    builder.setCancelable(true);

                    builder.show();

                }

            }
        });

        collegeIDFrontCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loaded) {

                    final Dialog builder = new Dialog(TenantDocumentsActivity.this);
                    builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    builder.getWindow().setBackgroundDrawable(
                            new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    ImageView imageView = new ImageView(getApplicationContext());

                    collegeIdFrontImageView.buildDrawingCache(true);

                    Bitmap bitmap = Bitmap.createBitmap(collegeIdFrontImageView.getDrawingCache(true));
                    imageView.setImageBitmap(bitmap);
                    builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                            1000,
                            1000));
                    builder.setCancelable(true);

                    builder.show();

                }

            }
        });

        collegeIDBackCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loaded) {

                    final Dialog builder = new Dialog(TenantDocumentsActivity.this);
                    builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    builder.getWindow().setBackgroundDrawable(
                            new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    ImageView imageView = new ImageView(getApplicationContext());

                    collegeIdBackImageView.buildDrawingCache(true);

                    Bitmap bitmap = Bitmap.createBitmap(collegeIdBackImageView.getDrawingCache(true));
                    imageView.setImageBitmap(bitmap);
                    builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                            1000,
                            1000));
                    builder.setCancelable(true);

                    builder.show();

                }
            }
        });

    }
}
