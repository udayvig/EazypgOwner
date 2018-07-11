package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailGeyser;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class GeyserDetailList extends ArrayAdapter<ApplianceDetailGeyser>{

    private Activity context;
    private List<ApplianceDetailGeyser> geyserList;

    public GeyserDetailList(Activity context, List<ApplianceDetailGeyser> geyserList) {
        super(context, R.layout.appliance_row, geyserList);

        this.context = context;
        this.geyserList = geyserList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemGeyser = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemGeyser.findViewById(R.id.firstTextView);
        TextView second = listViewItemGeyser.findViewById(R.id.secondTextView);
        TextView third = listViewItemGeyser.findViewById(R.id.thirdTextView);

        ApplianceDetailGeyser applianceDetailGeyser = geyserList.get(position);

        first.setText(applianceDetailGeyser.getRoomNo());
        second.setText(applianceDetailGeyser.getBrand());
        third.setText(applianceDetailGeyser.getCapacity());

        return listViewItemGeyser;
    }


}
