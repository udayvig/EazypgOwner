package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailRefrigerator;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class RefrigeratorDetailList extends ArrayAdapter<ApplianceDetailRefrigerator>{

    private Activity context;
    private List<ApplianceDetailRefrigerator> RefrigeratorList;

    public RefrigeratorDetailList(Activity context, List<ApplianceDetailRefrigerator> RefrigeratorList) {
        super(context, R.layout.appliance_row, RefrigeratorList);

        this.context = context;
        this.RefrigeratorList = RefrigeratorList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemRefrigerator = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemRefrigerator.findViewById(R.id.firstTextView);
        TextView second = listViewItemRefrigerator.findViewById(R.id.secondTextView);
        TextView third = listViewItemRefrigerator.findViewById(R.id.thirdTextView);

        ApplianceDetailRefrigerator applianceDetailRefrigerator= RefrigeratorList.get(position);

        first.setText(applianceDetailRefrigerator.getRoomNo());
        second.setText(applianceDetailRefrigerator.getBrand());
        third.setText(applianceDetailRefrigerator.getTypeOfRefrigerator());

        return listViewItemRefrigerator;
    }

}
