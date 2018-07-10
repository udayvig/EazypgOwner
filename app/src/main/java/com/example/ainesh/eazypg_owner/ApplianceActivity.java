package com.example.ainesh.eazypg_owner;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ainesh.eazypg_owner.Adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApplianceActivity extends AppCompatActivity{

    private Toolbar toolbar ;
    private Button addBtn ;

    private static View viewDialog;
    private RecyclerView applianceRecycler;
    Menu menu;
    public String[] appliances={"AC","Fan","Lift","Geyser","Washing Machine",
            "RO","Dishwasher","Microwave","Refrigerator","TV","CCTV","Iron",
            "Induction","Router","Heater","D2H","Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance);

        toolbar = findViewById(R.id.applianceToolbar);
        setSupportActionBar(toolbar);
        //  getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // menu = findViewById(R.id.)
        // addBtn = findViewById(R.id.addMoreBtn);
        RecyclerView mAppliancesList = findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAppliancesList.setLayoutManager(layoutManager);

        mAppliancesList.setHasFixedSize(true);

        ApplianceAdapter adapter=new ApplianceAdapter(appliances, getLayoutInflater(),new ApplianceAdapter.ClickListener(){
            @Override
            public void onPositionClicked(int position) {

                Toast.makeText(ApplianceActivity.this, "Test", Toast.LENGTH_SHORT).show();
            }
        });

        mAppliancesList.setAdapter(adapter);

    }



}