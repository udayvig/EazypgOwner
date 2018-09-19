package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.example.ainesh.eazypg_owner.R;

import io.fabric.sdk.android.Fabric;

public class FacilityComplaintsFragmentActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;

    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_complaints_fragment);

        Fabric.with(this, new Crashlytics());

        tabLayout = findViewById(R.id.tabLayoutID);
        pager = findViewById(R.id.viewPagerID);

        backButton= findViewById(R.id.backButton);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new FacilityUnresolvedFragment(), "Unresolved");
        adapter.addFragment(new FacilityResolvedFragment(), "Resolved");

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), ComplaintActivity.class));
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FacilityComplaintsFragmentActivity.this,ComplaintActivity.class));
        finish();
    }
}
