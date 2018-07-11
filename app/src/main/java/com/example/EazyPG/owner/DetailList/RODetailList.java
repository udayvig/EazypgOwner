package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailRO;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class RODetailList extends ArrayAdapter<ApplianceDetailRO>{

    private Activity context;
    private List<ApplianceDetailRO> ROList;

    public RODetailList(Activity context, List<ApplianceDetailRO> ROList) {
        super(context, R.layout.appliance_row, ROList);

        this.context = context;
        this.ROList = ROList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemRO = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemRO.findViewById(R.id.firstTextView);
        TextView second = listViewItemRO.findViewById(R.id.secondTextView);
        TextView third = listViewItemRO.findViewById(R.id.thirdTextView);

        ApplianceDetailRO applianceDetailRO= ROList.get(position);

        first.setText(applianceDetailRO.getRoomNo());
        second.setText(applianceDetailRO.getBrand());
        third.setText(applianceDetailRO.getTimeSinceInstallation());

        return listViewItemRO;
    }

}
