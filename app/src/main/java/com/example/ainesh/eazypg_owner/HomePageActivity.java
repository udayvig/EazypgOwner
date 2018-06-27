package com.example.ainesh.eazypg_owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.imangazaliev.circlemenu.CircleMenu;
import com.imangazaliev.circlemenu.CircleMenuButton;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        CircleMenu circleMenu = (CircleMenu) findViewById(R.id.circleMenu);
        circleMenu.setOnItemClickListener(new CircleMenu.OnItemClickListener() {
            @Override
            public void onItemClick(CircleMenuButton menuButton) {
                switch (menuButton.getId()) {
                    case R.id.pgView:
                        showMessage("YOUR PG");
                        startActivity(new Intent(HomePageActivity.this,MyPGActivity.class));
                        break;
                    case R.id.tenantView:
                        showMessage("TENANTS");
                        startActivity(new Intent(HomePageActivity.this,TenantActivity.class));
                        break;
                    case R.id.staffView:
                        showMessage("STAFF");
                        startActivity(new Intent(HomePageActivity.this,StaffActivity.class));
                        break;
                    case R.id.complainView:
                        showMessage("COMPLAINTS");
                        startActivity(new Intent(HomePageActivity.this,ComplaintActivity.class));
                        break;
                    case R.id.subscriptionView:
                        showMessage("SUBSCRIPTIONS");
                        startActivity(new Intent(HomePageActivity.this,SubscriptionActivity.class));
                        break;
                    case R.id.cashflowView:
                        showMessage("CASHFLOW");
                        startActivity(new Intent(HomePageActivity.this,CashflowActivity.class));
                }
            }
        });

        circleMenu.setStateUpdateListener(new CircleMenu.OnStateUpdateListener() {
            @Override
            public void onMenuExpanded() {
                Log.d("CircleMenuStatus", "Expanded");
            }

            @Override
            public void onMenuCollapsed() {
                Log.d("CircleMenuStatus", "Collapsed");
            }
        });

    }
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }}