package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailD2H;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class D2HDetailList extends ArrayAdapter<ApplianceDetailD2H>{

    private Activity context;
    private List<ApplianceDetailD2H> D2HList;

    public D2HDetailList(Activity context, List<ApplianceDetailD2H> D2HList) {
        super(context, R.layout.appliance_row, D2HList);

        this.context = context;
        this.D2HList = D2HList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemD2H = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemD2H.findViewById(R.id.firstTextView);
        TextView second = listViewItemD2H.findViewById(R.id.secondTextView);
        TextView third = listViewItemD2H.findViewById(R.id.thirdTextView);

        ApplianceDetailD2H applianceDetailD2H = D2HList.get(position);

        first.setText(applianceDetailD2H.getRoomNo());
        second.setText(applianceDetailD2H.getBrand());
        third.setText(applianceDetailD2H.getTimeSinceInstallation());

        return listViewItemD2H;
    }

}
