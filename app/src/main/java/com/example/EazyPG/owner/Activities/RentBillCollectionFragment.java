package com.example.EazyPG.owner.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ainesh.eazypg_owner.R;

public class RentBillCollectionFragment extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;

    ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_bill_collection_fragment);

        tabLayout = findViewById(R.id.tabLayoutID);
        pager = findViewById(R.id.viewPagerID);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new RentCollectionFragment(), "Rent");
        adapter.addFragment(new BillCollectionFragment(), "Bill");

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        backButton = findViewById(R.id.imageView3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RentBillCollectionFragment.this,HomePageActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RentBillCollectionFragment.this,HomePageActivity.class));
        finish();
    }
}
