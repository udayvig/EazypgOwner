package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailCCTV;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class CCTVDetailList extends ArrayAdapter<ApplianceDetailCCTV>{

    private Activity context;
    private List<ApplianceDetailCCTV> CCTVList;

    public CCTVDetailList(Activity context, List<ApplianceDetailCCTV> CCTVList) {
        super(context, R.layout.appliance_row, CCTVList);

        this.context = context;
        this.CCTVList = CCTVList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemCCTV = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemCCTV.findViewById(R.id.firstTextView);
        TextView second = listViewItemCCTV.findViewById(R.id.secondTextView);
        TextView third = listViewItemCCTV.findViewById(R.id.thirdTextView);

        ApplianceDetailCCTV applianceDetailCCTV = CCTVList.get(position);

        first.setText(applianceDetailCCTV.getRoomNo());
        second.setText(applianceDetailCCTV.getBrand());
        third.setText(applianceDetailCCTV.getTimeSinceInstallation());

        return listViewItemCCTV;
    }

}
