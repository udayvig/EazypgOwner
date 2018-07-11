package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailDishwasher;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class DishwasherDetailList extends ArrayAdapter<ApplianceDetailDishwasher>{

    private Activity context;
    private List<ApplianceDetailDishwasher> DishwasherList;

    public DishwasherDetailList(Activity context, List<ApplianceDetailDishwasher> DishwasherList) {
        super(context, R.layout.appliance_row, DishwasherList);

        this.context = context;
        this.DishwasherList = DishwasherList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemDishwasher = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemDishwasher.findViewById(R.id.firstTextView);
        TextView second = listViewItemDishwasher.findViewById(R.id.secondTextView);
        TextView third = listViewItemDishwasher.findViewById(R.id.thirdTextView);

        ApplianceDetailDishwasher applianceDetailDishwasher= DishwasherList.get(position);

        first.setText(applianceDetailDishwasher.getRoomNo());
        second.setText(applianceDetailDishwasher.getBrand());
        third.setText(applianceDetailDishwasher.getTimeSinceInstallation());

        return listViewItemDishwasher;
    }

}
