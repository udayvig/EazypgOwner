package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ainesh.eazypg_owner.R;

public class ApplianceActivity extends AppCompatActivity {

    Button acButton, cctvButton, d2hButton, dishwasherButton, fanButton, geyserButton, heaterButton, inductionButton, ironButton, liftButton, microwaveButton, refrigeratorButton, roButton, routerButton, tvButton, washingMachineButton, otherButton;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance);

        acButton = findViewById(R.id.acButton);
        cctvButton = findViewById(R.id.cctvButton);
        d2hButton = findViewById(R.id.d2hButton);
        dishwasherButton = findViewById(R.id.dishwasherButton);
        fanButton = findViewById(R.id.fanButton);
        geyserButton = findViewById(R.id.geyserButton);
        heaterButton = findViewById(R.id.heaterButton);
        inductionButton = findViewById(R.id.inductionButton);
        ironButton = findViewById(R.id.ironButton);
        liftButton = findViewById(R.id.liftButton);
        microwaveButton = findViewById(R.id.microwaveButton);
        refrigeratorButton = findViewById(R.id.refrigeratorButton);
        roButton = findViewById(R.id.roButton);
        routerButton = findViewById(R.id.routerButton);
        tvButton = findViewById(R.id.tvButton);
        washingMachineButton = findViewById(R.id.washingMachineButton);
        otherButton = findViewById(R.id.otherButton);

        acButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "AC");
                startActivity(intent);
                finish();
            }
        });

        cctvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "CCTV");
                startActivity(intent);
                finish();
            }
        });

        dishwasherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Dishwasher");
                startActivity(intent);
                finish();
            }
        });

        fanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Fan");
                startActivity(intent);
                finish();
            }
        });

        geyserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Geyser");
                startActivity(intent);
                finish();
            }
        });

        heaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Heater");
                startActivity(intent);
                finish();
            }
        });

        inductionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Induction");
                startActivity(intent);
                finish();
            }
        });

        ironButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Iron");
                startActivity(intent);
                finish();
            }
        });

        liftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Lift");
                startActivity(intent);
                finish();
            }
        });

        microwaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Microwave");
                startActivity(intent);
                finish();
            }
        });

        refrigeratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Refrigerator");
                startActivity(intent);
                finish();
            }
        });

        roButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "RO");
                startActivity(intent);
                finish();
            }
        });

        routerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Router");
                startActivity(intent);
                finish();
            }
        });

        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "TV");
                startActivity(intent);
                finish();
            }
        });

        washingMachineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Washing Machine");
                startActivity(intent);
                finish();
            }
        });

        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplianceActivity.this, ApplianceDetailsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Other");
                startActivity(intent);
                finish();
            }
        });

    }
}
