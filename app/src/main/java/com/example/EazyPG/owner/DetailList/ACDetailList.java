package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailAC;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class ACDetailList extends ArrayAdapter<ApplianceDetailAC>{

    private Activity context;
    private List<ApplianceDetailAC> acList;

    public ACDetailList(Activity context, List<ApplianceDetailAC> acList) {
        super(context, R.layout.appliance_row, acList);

        this.context = context;
        this.acList = acList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemAC = inflater.inflate(R.layout.appliance_row, null, true);

        listViewItemAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked.", Toast.LENGTH_SHORT).show();
            }
        });

        TextView first = listViewItemAC.findViewById(R.id.firstTextView);
        TextView second = listViewItemAC.findViewById(R.id.secondTextView);
        TextView third = listViewItemAC.findViewById(R.id.thirdTextView);

        ApplianceDetailAC applianceDetailAC = acList.get(position);

        first.setText(applianceDetailAC.getRoomNo());
        second.setText(applianceDetailAC.getBrand());
        third.setText(applianceDetailAC.getLastServiceDate());

        return listViewItemAC;
    }


}
