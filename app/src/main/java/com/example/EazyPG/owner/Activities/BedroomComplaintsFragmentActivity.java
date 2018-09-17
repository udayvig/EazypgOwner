package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ainesh.eazypg_owner.R;

public class BedroomComplaintsFragmentActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedroom_complaints_fragment);

        tabLayout = findViewById(R.id.tabLayoutID);
        pager = findViewById(R.id.viewPagerID);

        backButton = findViewById(R.id.imageView3);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new BedroomUnresolvedFragment(), "Unresolved");
        adapter.addFragment(new BedroomResolvedFragment(), "Resolved");

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BedroomComplaintsFragmentActivity.this,ComplaintActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BedroomComplaintsFragmentActivity.this, ComplaintActivity.class));
        finish();
    }
}
