package com.example.EazyPG.owner.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.example.EazyPG.owner.Adapter.MyAdapter;
import com.example.EazyPG.owner.Model.Item;
import com.example.ainesh.eazypg_owner.NotificationActivity;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.fabric.sdk.android.Fabric;

public class HomePageActivity extends AppCompatActivity {



    CardView profileCard;
    ImageView notifications;
    CardView accounts;
    CardView rentBill;
    CardView addBill;
    CardView tenant;
    CardView room;
    CardView feedback;
    CardView staff;
    CardView food;
    CardView complaints;
    ImageView appliances;


    private  int someVarA;
    private  String someVarB;

    SwitchCompat logout;

    List<Item> items = new ArrayList<>();
    MyAdapter adapter;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Fabric.with(this, new Crashlytics());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(19,65,62));
        }



        profileCard = findViewById(R.id.profileCard);
        notifications = findViewById(R.id.notificationImage);
        accounts = findViewById(R.id.accountsCardView);
        rentBill = findViewById(R.id.rentBillCollectionCard);
        addBill = findViewById(R.id.addBillButton);
        tenant = findViewById(R.id.tenantImageView);
        room = findViewById(R.id.roomCardView);
        feedback = findViewById(R.id.feedbackCardView);
        staff = findViewById(R.id.staffImageView);
        food = findViewById(R.id.foodButton);
        appliances = findViewById(R.id.appliancesCardView);
        complaints = findViewById(R.id.complaintsCardView);

        firebaseAuth = FirebaseAuth.getInstance();

        tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, TenantActivity.class));
                finish();
            }
        });

        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this , RoomsActivity.class));
                finish();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, FeedbackActivity.class));
                finish();
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, FoodActivity.class));
                finish();
            }
        });

        appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, ApplianceActivity.class));
                finish();
            }
        });

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, StaffActivity.class));
                finish();
            }
        });

        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, MyPGActivity.class));
                finish();
            }
        });

        accounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, AccountsFragmentActivity.class));
                finish();
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this , LogFragmentActivity.class));
                finish();
            }
        });

       rentBill.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(HomePageActivity.this , RentBillCollectionFragment.class));
               finish();
           }
       });

        addBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, AddBillActivity.class));
                finish();
            }
        });

        complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, ComplaintActivity.class));
                finish();
            }
        });



    }



    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("someVarA", someVarA);
        outState.putString("someVarB", someVarB);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        someVarA = savedInstanceState.getInt("someVarA");
        someVarB = savedInstanceState.getString("someVarB");
    }

}