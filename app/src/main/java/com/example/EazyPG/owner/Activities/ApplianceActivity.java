package com.example.EazyPG.owner.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.ainesh.eazypg_owner.R;

public class ApplianceActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private Button addBtn;
    public String[] appliances={"AC","Fan","Lift","Geyser","Washing Machine",
            "RO","Dishwasher","Microwave","Refrigerator","TV","CCTV","Iron",
            "Induction","Router","Heater","D2H","Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance);

        toolbar = findViewById(R.id.applianceToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        RecyclerView mAppliancesList = findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAppliancesList.setLayoutManager(layoutManager);

        mAppliancesList.setHasFixedSize(true);

        ApplianceAdapter adapter = new ApplianceAdapter(appliances, getLayoutInflater(),null);

        mAppliancesList.setAdapter(adapter);

    }



}