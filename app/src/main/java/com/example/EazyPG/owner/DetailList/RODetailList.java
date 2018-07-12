package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailRO;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class RODetailList extends ArrayAdapter<ApplianceDetailRO>{

    private Activity context;
    private List<ApplianceDetailRO> ROList;

    public RODetailList(Activity context, List<ApplianceDetailRO> ROList) {
        super(context, R.layout.appliance_row, ROList);

        this.context = context;
        this.ROList = ROList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemRO = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemRO.findViewById(R.id.firstTextView);
        TextView second = listViewItemRO.findViewById(R.id.secondTextView);
        TextView third = listViewItemRO.findViewById(R.id.thirdTextView);

        final ApplianceDetailRO applianceDetailRO= ROList.get(position);


        listViewItemRO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ROCapacity, ROCompanyName, RODays, ROModel, RORoomNo;

                ROCapacity = view.findViewById(R.id.ROCapacityEditText);
                ROCompanyName = view.findViewById(R.id.ROCompanyNameEditText);
                RODays = view.findViewById(R.id.RODaysEditText);
                ROModel = view.findViewById(R.id.ROModelEditText);
                RORoomNo = view.findViewById(R.id.RORoomNumberEditText);

                RelativeLayout ACLayout, fanLayout, liftLayout, geyserLayout, washingMachineLayout, ROLayout, dishwasherLayout, microwaveLayout,
                        fridgeLayout, TVLayout, CCTVLayout, ironLayout, inductionLayout, routerLayout, heaterLayout, D2HLayout, otherLayout;

                ACLayout = viewDialog.findViewById(R.id.ACLayout);
                fanLayout = viewDialog.findViewById(R.id.fanLayout);
                liftLayout = viewDialog.findViewById(R.id.liftLayout);
                geyserLayout = viewDialog.findViewById(R.id.geyserLayout);
                washingMachineLayout = viewDialog.findViewById(R.id.washingMachineLayout);
                ROLayout = viewDialog.findViewById(R.id.ROLayout);
                dishwasherLayout = viewDialog.findViewById(R.id.dishwasherLayout);
                microwaveLayout = viewDialog.findViewById(R.id.microwaveLayout);
                fridgeLayout = viewDialog.findViewById(R.id.fridgeLayout);
                TVLayout = viewDialog.findViewById(R.id.TVLayout);
                CCTVLayout = viewDialog.findViewById(R.id.CCTVLayout);
                ironLayout = viewDialog.findViewById(R.id.ironLayout);
                inductionLayout = viewDialog.findViewById(R.id.inductionLayout);
                routerLayout = viewDialog.findViewById(R.id.routerLayout);
                heaterLayout = viewDialog.findViewById(R.id.heaterLayout);
                D2HLayout = viewDialog.findViewById(R.id.D2HLayout);
                otherLayout = viewDialog.findViewById(R.id.otherLayout);

                ACLayout.setVisibility(View.GONE);
                fanLayout.setVisibility(View.GONE);
                liftLayout.setVisibility(View.GONE);
                geyserLayout.setVisibility(View.GONE);
                washingMachineLayout.setVisibility(View.GONE);
                ROLayout.setVisibility(View.VISIBLE);
                dishwasherLayout.setVisibility(View.GONE);
                microwaveLayout.setVisibility(View.GONE);
                fridgeLayout.setVisibility(View.GONE);
                TVLayout.setVisibility(View.GONE);
                CCTVLayout.setVisibility(View.GONE);
                ironLayout.setVisibility(View.GONE);
                inductionLayout.setVisibility(View.GONE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("RO" + " details");

                ROCapacity.setText(applianceDetailRO.capacity);
                ROCompanyName.setText(applianceDetailRO.brand);
                RODays.setText(applianceDetailRO.timeSinceInstallation);
                ROModel.setText(applianceDetailRO.model);
                ROModel.setText(applianceDetailRO.model);
                RORoomNo.setText(applianceDetailRO.roomNo);

                builder.setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.setNegativeButton("Cancel",null);

                builder.show();
            }
        });


        first.setText(applianceDetailRO.getRoomNo());
        second.setText(applianceDetailRO.getBrand());
        third.setText(applianceDetailRO.getTimeSinceInstallation());

        return listViewItemRO;
    }

}
