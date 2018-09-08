package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.ainesh.eazypg_owner.R;

public class AddBillActivity extends AppCompatActivity {

    public static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    CardView electricityBillCardView, wifiBillCardView, gasBillCardView, otherBillCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        electricityBillCardView = findViewById(R.id.electricityBillCardView);
        wifiBillCardView = findViewById(R.id.wifiBillCardView);
        gasBillCardView = findViewById(R.id.gasBillCardView);
        otherBillCardView = findViewById(R.id.otherBillCardView);

        electricityBillCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBillActivity.this, AddBillRoomActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Electricity");
                startActivity(intent);
            }
        });

        wifiBillCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBillActivity.this, AddBillRoomActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Wifi");
                startActivity(intent);
            }
        });

        gasBillCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBillActivity.this, AddBillRoomActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Gas");
                startActivity(intent);
            }
        });

        otherBillCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBillActivity.this, AddBillRoomActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Other");
                startActivity(intent);
            }
        });
    }
}
