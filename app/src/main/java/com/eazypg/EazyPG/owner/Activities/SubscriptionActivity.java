package com.eazypg.EazyPG.owner.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.eazypg.ainesh.eazypg_owner.R;

import io.fabric.sdk.android.Fabric;

public class SubscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        Fabric.with(this, new Crashlytics());
    }
}
