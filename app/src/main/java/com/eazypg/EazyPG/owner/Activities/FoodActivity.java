package com.eazypg.EazyPG.owner.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.eazypg.EazyPG.owner.Activities.FoodDetails.FridayFoodDetails;
import com.eazypg.EazyPG.owner.Activities.FoodDetails.MondayFoodDetails;
import com.eazypg.EazyPG.owner.Activities.FoodDetails.SaturdayFoodDetails;
import com.eazypg.EazyPG.owner.Activities.FoodDetails.SundayFoodDetails;
import com.eazypg.EazyPG.owner.Activities.FoodDetails.ThursdayFoodDetails;
import com.eazypg.EazyPG.owner.Activities.FoodDetails.TuesdayFoodDetails;
import com.eazypg.EazyPG.owner.Activities.FoodDetails.WednesdayFoodDetails;
import com.eazypg.ainesh.eazypg_owner.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.fabric.sdk.android.Fabric;

public class FoodActivity extends AppCompatActivity {

    EditText mondayBreakfastEditText, mondayLunchEditText, mondayDinnerEditText;
    EditText tuesdayBreakfastEditText, tuesdayLunchEditText, tuesdayDinnerEditText;
    EditText wednesdayBreakfastEditText, wednesdayLunchEditText, wednesdayDinnerEditText;
    EditText thursdayBreakfastEditText, thursdayLunchEditText, thursdayDinnerEditText;
    EditText fridayBreakfastEditText, fridayLunchEditText, fridayDinnerEditText;
    EditText saturdayBreakfastEditText, saturdayLunchEditText, saturdayDinnerEditText;
    EditText sundayBreakfastEditText, sundayLunchEditText, sundayDinnerEditText;

    CardView mondayCard;
    Button saveFoodButton;
    HorizontalScrollView scrollView;

    ImageView backButton;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReference1;

    TextView confirmedTextView, maybeTextView, leavesTextView;
    TextView savedBreakFastTextView, savedLunchTextView, savedDinnerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Fabric.with(this, new Crashlytics());
/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(0,60,74));
        }*/
        backButton = findViewById(R.id.imageView3);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();


        scrollView = findViewById(R.id.horizontalScrollView);
        scrollView.smoothScrollBy(0,0);
        mondayCard = findViewById(R.id.mondayCardView);


        mondayCard.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

        confirmedTextView = findViewById(R.id.confirmedTextView);
        maybeTextView = findViewById(R.id.maybeTextView);
        leavesTextView = findViewById(R.id.leavesTextView);

        savedBreakFastTextView = findViewById(R.id.savedBreakFastTextView);
        savedLunchTextView = findViewById(R.id.savedLunchTextView);
        savedDinnerTextView = findViewById(R.id.savedDinnerTextView);

        {
            mondayBreakfastEditText = findViewById(R.id.mondayBreakfastEditText);
            mondayLunchEditText = findViewById(R.id.mondayLunchEditText);
            mondayDinnerEditText = findViewById(R.id.mondayDinnerEditText);

            tuesdayBreakfastEditText = findViewById(R.id.tuesdayBreakfastEditText);
            tuesdayLunchEditText = findViewById(R.id.tuesdayLunchEditText);
            tuesdayDinnerEditText = findViewById(R.id.tuesdayDinnerEditText);

            wednesdayBreakfastEditText = findViewById(R.id.wednesdayBreakfastEditText);
            wednesdayLunchEditText = findViewById(R.id.wednesdayLunchEditText);
            wednesdayDinnerEditText = findViewById(R.id.wednesdayDinnerEditText);

            thursdayBreakfastEditText = findViewById(R.id.thursdayBreakfastEditText);
            thursdayLunchEditText = findViewById(R.id.thursdayLunchEditText);
            thursdayDinnerEditText = findViewById(R.id.thursdayDinnerEditText);

            fridayBreakfastEditText = findViewById(R.id.fridayBreakfastEditText);
            fridayLunchEditText = findViewById(R.id.fridayLunchEditText);
            fridayDinnerEditText = findViewById(R.id.fridayDinnerEditText);

            saturdayBreakfastEditText = findViewById(R.id.saturdayBreakfastEditText);
            saturdayLunchEditText = findViewById(R.id.saturdayLunchEditText);
            saturdayDinnerEditText = findViewById(R.id.saturdayDinnerEditText);

            sundayBreakfastEditText = findViewById(R.id.sundayBreakfastEditText);
            sundayLunchEditText = findViewById(R.id.sundayLunchEditText);
            sundayDinnerEditText = findViewById(R.id.sundayDinnerEditText);

            saveFoodButton = findViewById(R.id.saveFoodButton);

        }

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Food Details/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                MondayFoodDetails mondayFoodDetails = dataSnapshot.child("Monday").getValue(MondayFoodDetails.class);
                TuesdayFoodDetails tuesdayFoodDetails = dataSnapshot.child("Tuesday").getValue(TuesdayFoodDetails.class);
                WednesdayFoodDetails wednesdayFoodDetails = dataSnapshot.child("Wednesday").getValue(WednesdayFoodDetails.class);
                ThursdayFoodDetails thursdayFoodDetails = dataSnapshot.child("Thursday").getValue(ThursdayFoodDetails.class);
                FridayFoodDetails fridayFoodDetails = dataSnapshot.child("Friday").getValue(FridayFoodDetails.class);
                SaturdayFoodDetails saturdayFoodDetails = dataSnapshot.child("Saturday").getValue(SaturdayFoodDetails.class);
                SundayFoodDetails sundayFoodDetails = dataSnapshot.child("Sunday").getValue(SundayFoodDetails.class);

                {
                    if (mondayFoodDetails != null) {

                        mondayBreakfastEditText.setText(mondayFoodDetails.breakfast);
                        mondayLunchEditText.setText(mondayFoodDetails.lunch);
                        mondayDinnerEditText.setText(mondayFoodDetails.dinner);
                    }

                    if (tuesdayFoodDetails != null) {

                        tuesdayBreakfastEditText.setText(tuesdayFoodDetails.breakfast);
                        tuesdayLunchEditText.setText(tuesdayFoodDetails.lunch);
                        tuesdayDinnerEditText.setText(tuesdayFoodDetails.dinner);

                    }

                    if (wednesdayFoodDetails != null) {

                        wednesdayBreakfastEditText.setText(wednesdayFoodDetails.breakfast);
                        wednesdayLunchEditText.setText(wednesdayFoodDetails.lunch);
                        wednesdayDinnerEditText.setText(wednesdayFoodDetails.dinner);

                    }

                    if (thursdayFoodDetails != null) {

                        thursdayBreakfastEditText.setText(thursdayFoodDetails.breakfast);
                        thursdayLunchEditText.setText(thursdayFoodDetails.lunch);
                        thursdayDinnerEditText.setText(thursdayFoodDetails.dinner);

                    }

                    if (fridayFoodDetails != null) {

                        fridayBreakfastEditText.setText(fridayFoodDetails.breakfast);
                        fridayLunchEditText.setText(fridayFoodDetails.lunch);
                        fridayDinnerEditText.setText(fridayFoodDetails.dinner);

                    }

                    if (saturdayFoodDetails != null) {

                        saturdayBreakfastEditText.setText(saturdayFoodDetails.breakfast);
                        saturdayLunchEditText.setText(saturdayFoodDetails.lunch);
                        saturdayDinnerEditText.setText(saturdayFoodDetails.dinner);

                    }

                    if (sundayFoodDetails != null) {

                        sundayBreakfastEditText.setText(sundayFoodDetails.breakfast);
                        sundayLunchEditText.setText(sundayFoodDetails.lunch);
                        sundayDinnerEditText.setText(sundayFoodDetails.dinner);

                    }
                }   // Week food retrieved

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Meals Saved/");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                confirmedTextView.setText(dataSnapshot.child("Upcoming Meal").child("Yes").getValue(String.class));
                maybeTextView.setText(dataSnapshot.child("Upcoming Meal").child("Maybe").getValue(String.class));
                leavesTextView.setText(dataSnapshot.child("Upcoming Meal").child("No").getValue(String.class));

                savedBreakFastTextView.setText(dataSnapshot.child("Breakfast").child("No").getValue(String.class));
                savedDinnerTextView.setText(dataSnapshot.child("Dinner").child("No").getValue(String.class));
                savedLunchTextView.setText(dataSnapshot.child("Lunch").child("No").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        saveFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = ProgressDialog.show(FoodActivity.this, "Saving", "Saving Food Details..", true);

                databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Food Details/");

                MondayFoodDetails mondayFoodDetails = new MondayFoodDetails(mondayBreakfastEditText.getText().toString(), mondayLunchEditText.getText().toString(), mondayDinnerEditText.getText().toString());
                databaseReference.child("Monday").setValue(mondayFoodDetails);

                TuesdayFoodDetails tuesdayFoodDetails = new TuesdayFoodDetails(tuesdayBreakfastEditText.getText().toString(), tuesdayLunchEditText.getText().toString(), tuesdayDinnerEditText.getText().toString());
                databaseReference.child("Tuesday").setValue(tuesdayFoodDetails);

                WednesdayFoodDetails wednesdayFoodDetails = new WednesdayFoodDetails(wednesdayBreakfastEditText.getText().toString(), wednesdayLunchEditText.getText().toString(), wednesdayDinnerEditText.getText().toString());
                databaseReference.child("Wednesday").setValue(wednesdayFoodDetails);

                ThursdayFoodDetails thursdayFoodDetails = new ThursdayFoodDetails(thursdayBreakfastEditText.getText().toString(), thursdayLunchEditText.getText().toString(), thursdayDinnerEditText.getText().toString());
                databaseReference.child("Thursday").setValue(thursdayFoodDetails);

                FridayFoodDetails fridayFoodDetails = new FridayFoodDetails(fridayBreakfastEditText.getText().toString(), fridayLunchEditText.getText().toString(), fridayDinnerEditText.getText().toString());
                databaseReference.child("Friday").setValue(fridayFoodDetails);

                SaturdayFoodDetails saturdayFoodDetails = new SaturdayFoodDetails(saturdayBreakfastEditText.getText().toString(), saturdayLunchEditText.getText().toString(), saturdayDinnerEditText.getText().toString());
                databaseReference.child("Saturday").setValue(saturdayFoodDetails);

                SundayFoodDetails sundayFoodDetails = new SundayFoodDetails(sundayBreakfastEditText.getText().toString(), sundayLunchEditText.getText().toString(), sundayDinnerEditText.getText().toString());
                databaseReference.child("Sunday").setValue(sundayFoodDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        progressDialog.dismiss();
                        Toast.makeText(FoodActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodActivity.this,HomePageActivity.class));
                finish();
            }
        });



    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FoodActivity.this , HomePageActivity.class));
        finish();
    }
}
