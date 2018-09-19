package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.EazyPG.owner.DetailList.TenantProfileFragment;
import com.example.ainesh.eazypg_owner.R;

public class TenantDashboardFragmentActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;

    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_dashboard_fragment);

        tabLayout = findViewById(R.id.tabLayoutID);
        pager = findViewById(R.id.viewPagerID);

        backButton = findViewById(R.id.imageView3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TenantDashboardFragmentActivity.this, TenantActivity.class));
                finish();

            }
        });


        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new TenantProfileFragment(), "Profile");
        adapter.addFragment(new TenantDocumentsFragment(), "Documents");

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TenantDashboardFragmentActivity.this, TenantActivity.class));
        finish();
    }
}
