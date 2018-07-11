package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailLift;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class LiftDetailList extends ArrayAdapter<ApplianceDetailLift>{

    private Activity context;
    private List<ApplianceDetailLift> liftList;

    public LiftDetailList(Activity context, List<ApplianceDetailLift> liftList) {
        super(context, R.layout.appliance_row, liftList);

        this.context = context;
        this.liftList = liftList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemLift = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemLift.findViewById(R.id.firstTextView);
        TextView second = listViewItemLift.findViewById(R.id.secondTextView);
        TextView third = listViewItemLift.findViewById(R.id.thirdTextView);

        ApplianceDetailLift applianceDetailLift = liftList.get(position);

        first.setText(applianceDetailLift.getBrand());
        second.setText(applianceDetailLift.getDoorType());
        third.setText(applianceDetailLift.getTimeSinceInstallation());

        return listViewItemLift;
    }


}
