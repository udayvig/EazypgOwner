
package com.example.EazyPG.owner.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crashlytics.android.Crashlytics;
import com.example.ainesh.eazypg_owner.R;

import io.fabric.sdk.android.Fabric;

public class SecurityUnresolvedFragment extends Fragment {

    public SecurityUnresolvedFragment() {
    }
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Fabric.with(getContext(), new Crashlytics());
        view = inflater.inflate(R.layout.security_unresolved_fragment, container, false);
        return view;
    }
}
