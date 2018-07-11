package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailIron;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class IronDetailList extends ArrayAdapter<ApplianceDetailIron>{

    private Activity context;
    private List<ApplianceDetailIron> IronList;

    public IronDetailList(Activity context, List<ApplianceDetailIron> IronList) {
        super(context, R.layout.appliance_row, IronList);

        this.context = context;
        this.IronList = IronList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemIron = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemIron.findViewById(R.id.firstTextView);
        TextView second = listViewItemIron.findViewById(R.id.secondTextView);
        TextView third = listViewItemIron.findViewById(R.id.thirdTextView);

        ApplianceDetailIron applianceDetailIron = IronList.get(position);

        first.setText(applianceDetailIron.getRoomNo());
        second.setText(applianceDetailIron.getBrand());
        third.setText(applianceDetailIron.getTimeSinceInstallation());

        return listViewItemIron;
    }

}
