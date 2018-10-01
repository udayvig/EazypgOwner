package com.eazypg.EazyPG.owner.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crashlytics.android.Crashlytics;
import com.eazypg.ainesh.eazypg_owner.R;

import io.fabric.sdk.android.Fabric;

public class FoodResolvedFragment extends Fragment {

    public FoodResolvedFragment() {
    }
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Fabric.with(getContext(), new Crashlytics());
        view = inflater.inflate(R.layout.food_resolved_fragment, container, false);

        return view;
    }
}
