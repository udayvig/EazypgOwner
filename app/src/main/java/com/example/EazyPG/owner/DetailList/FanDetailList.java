package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailFan;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class FanDetailList extends ArrayAdapter<ApplianceDetailFan> {

    private Activity context;
    private List<ApplianceDetailFan> fanList;

    public FanDetailList(Activity context, List<ApplianceDetailFan> fanList) {
        super(context, R.layout.appliance_row, fanList);

        this.context = context;
        this.fanList = fanList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemFan = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemFan.findViewById(R.id.firstTextView);
        TextView second = listViewItemFan.findViewById(R.id.secondTextView);
        TextView third = listViewItemFan.findViewById(R.id.thirdTextView);

        ApplianceDetailFan applianceDetailFan = fanList.get(position);

        first.setText(applianceDetailFan.getRoomNo());
        second.setText(applianceDetailFan.getBrand());
        third.setText(applianceDetailFan.getTimeSinceInstallation());

        return listViewItemFan;
    }


}

