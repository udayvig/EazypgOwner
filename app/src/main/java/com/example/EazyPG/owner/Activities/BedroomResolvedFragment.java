package com.example.EazyPG.owner.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ainesh.eazypg_owner.R;

public class BedroomResolvedFragment extends Fragment {

    public BedroomResolvedFragment() {
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.bedroom_resolved_fragment, container, false);


        return view;
    }
}