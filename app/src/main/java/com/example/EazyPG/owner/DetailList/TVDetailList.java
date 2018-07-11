package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailTV;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class TVDetailList extends ArrayAdapter<ApplianceDetailTV>{

    private Activity context;
    private List<ApplianceDetailTV> TVList;

    public TVDetailList(Activity context, List<ApplianceDetailTV> TVList) {
        super(context, R.layout.appliance_row, TVList);

        this.context = context;
        this.TVList = TVList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemTV = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemTV.findViewById(R.id.firstTextView);
        TextView second = listViewItemTV.findViewById(R.id.secondTextView);
        TextView third = listViewItemTV.findViewById(R.id.thirdTextView);

        ApplianceDetailTV applianceDetailTV= TVList.get(position);

        first.setText(applianceDetailTV.getRoomNo());
        second.setText(applianceDetailTV.getBrand());
        third.setText(applianceDetailTV.getTypeOfTV());

        return listViewItemTV;
    }

}
