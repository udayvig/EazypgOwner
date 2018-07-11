package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailMicrowave;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class MicrowaveDetailList extends ArrayAdapter<ApplianceDetailMicrowave>{

    private Activity context;
    private List<ApplianceDetailMicrowave> MicrowaveList;

    public MicrowaveDetailList(Activity context, List<ApplianceDetailMicrowave> MicrowaveList) {
        super(context, R.layout.appliance_row, MicrowaveList);

        this.context = context;
        this.MicrowaveList = MicrowaveList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemMicrowave = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemMicrowave.findViewById(R.id.firstTextView);
        TextView second = listViewItemMicrowave.findViewById(R.id.secondTextView);
        TextView third = listViewItemMicrowave.findViewById(R.id.thirdTextView);

        ApplianceDetailMicrowave applianceDetailMicrowave= MicrowaveList.get(position);

        first.setText(applianceDetailMicrowave.getRoomNo());
        second.setText(applianceDetailMicrowave.getBrand());
        third.setText(applianceDetailMicrowave.getCapacity());

        return listViewItemMicrowave;
    }

}
