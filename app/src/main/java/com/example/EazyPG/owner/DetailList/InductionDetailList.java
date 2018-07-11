package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailInduction;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class InductionDetailList extends ArrayAdapter<ApplianceDetailInduction>{

    private Activity context;
    private List<ApplianceDetailInduction> InductionList;

    public InductionDetailList(Activity context, List<ApplianceDetailInduction> InductionList) {
        super(context, R.layout.appliance_row, InductionList);

        this.context = context;
        this.InductionList = InductionList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemInduction = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemInduction.findViewById(R.id.firstTextView);
        TextView second = listViewItemInduction.findViewById(R.id.secondTextView);
        TextView third = listViewItemInduction.findViewById(R.id.thirdTextView);

        ApplianceDetailInduction applianceDetailInduction = InductionList.get(position);

        first.setText(applianceDetailInduction.getRoomNo());
        second.setText(applianceDetailInduction.getBrand());
        third.setText(applianceDetailInduction.getControlType());

        return listViewItemInduction;
    }

}
