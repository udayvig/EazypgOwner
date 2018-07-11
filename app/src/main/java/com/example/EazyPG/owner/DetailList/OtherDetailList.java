package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailOther;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class OtherDetailList extends ArrayAdapter<ApplianceDetailOther>{

    private Activity context;
    private List<ApplianceDetailOther> OtherList;

    public OtherDetailList(Activity context, List<ApplianceDetailOther> OtherList) {
        super(context, R.layout.appliance_row, OtherList);

        this.context = context;
        this.OtherList = OtherList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemOther = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemOther.findViewById(R.id.firstTextView);
        TextView second = listViewItemOther.findViewById(R.id.secondTextView);
        TextView third = listViewItemOther.findViewById(R.id.thirdTextView);

        ApplianceDetailOther applianceDetailOther= OtherList.get(position);

        first.setText(applianceDetailOther.getRoomNo());
        second.setText(applianceDetailOther.getApplianceName());
        third.setText(applianceDetailOther.getBrand());

        return listViewItemOther;
    }

}
