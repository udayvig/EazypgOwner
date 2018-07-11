package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailHeater;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class HeaterDetailList extends ArrayAdapter<ApplianceDetailHeater>{

    private Activity context;
    private List<ApplianceDetailHeater> HeaterList;

    public HeaterDetailList(Activity context, List<ApplianceDetailHeater> HeaterList) {
        super(context, R.layout.appliance_row, HeaterList);

        this.context = context;
        this.HeaterList = HeaterList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemHeater = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemHeater.findViewById(R.id.firstTextView);
        TextView second = listViewItemHeater.findViewById(R.id.secondTextView);
        TextView third = listViewItemHeater.findViewById(R.id.thirdTextView);

        ApplianceDetailHeater applianceDetailHeater= HeaterList.get(position);

        first.setText(applianceDetailHeater.getRoomNo());
        second.setText(applianceDetailHeater.getBrand());
        third.setText(applianceDetailHeater.getInputPower());

        return listViewItemHeater;
    }

}
