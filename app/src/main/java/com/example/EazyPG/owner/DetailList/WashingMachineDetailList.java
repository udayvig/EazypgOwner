package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailWashingMachine;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class WashingMachineDetailList extends ArrayAdapter<ApplianceDetailWashingMachine>{

    private Activity context;
    private List<ApplianceDetailWashingMachine> washingMachineList;

    public WashingMachineDetailList(Activity context, List<ApplianceDetailWashingMachine> washingMachineList) {
        super(context, R.layout.appliance_row, washingMachineList);

        this.context = context;
        this.washingMachineList = washingMachineList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemWashingMachine = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemWashingMachine.findViewById(R.id.firstTextView);
        TextView second = listViewItemWashingMachine.findViewById(R.id.secondTextView);
        TextView third = listViewItemWashingMachine.findViewById(R.id.thirdTextView);

        ApplianceDetailWashingMachine applianceDetailWashingMachine = washingMachineList.get(position);

        first.setText(applianceDetailWashingMachine.getRoomNo());
        second.setText(applianceDetailWashingMachine.getBrand());
        third.setText(applianceDetailWashingMachine.getTypeOfMachine());

        return listViewItemWashingMachine;
    }

}
