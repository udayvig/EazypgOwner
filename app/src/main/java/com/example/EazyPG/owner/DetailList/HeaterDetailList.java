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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailHeater;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class HeaterDetailList extends ArrayAdapter<ApplianceDetailHeater>{

    private Activity context;
    private List<ApplianceDetailHeater> HeaterList;

    public HeaterDetailList(Activity context, List<ApplianceDetailHeater> HeaterList) {
        super(context, R.layout.appliance_row, HeaterList);

        this.context = context;
        this.HeaterList = HeaterList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemHeater = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemHeater.findViewById(R.id.firstTextView);
        TextView second = listViewItemHeater.findViewById(R.id.secondTextView);
        TextView third = listViewItemHeater.findViewById(R.id.thirdTextView);

        final ApplianceDetailHeater applianceDetailHeater= HeaterList.get(position);

        listViewItemHeater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText HeaterCompanyName, HeaterDays, HeaterModel, HeaterPower, HeaterRoomNo,HeaterWeight;

                HeaterCompanyName = view.findViewById(R.id.heaterCompanyNameEditText);
                HeaterDays = view.findViewById(R.id.heaterDaysEditText);
                HeaterModel = view.findViewById(R.id.heaterModelEditText);
                HeaterPower = view.findViewById(R.id.heaterPowerEditText);
                HeaterRoomNo = view.findViewById(R.id.heaterRoomNumberEditText);
                HeaterWeight = view.findViewById(R.id.heaterWeightEditText);

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
                ROLayout.setVisibility(View.GONE);
                dishwasherLayout.setVisibility(View.GONE);
                microwaveLayout.setVisibility(View.GONE);
                fridgeLayout.setVisibility(View.GONE);
                TVLayout.setVisibility(View.GONE);
                CCTVLayout.setVisibility(View.GONE);
                ironLayout.setVisibility(View.GONE);
                inductionLayout.setVisibility(View.GONE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.VISIBLE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Heater" + " details");

                HeaterCompanyName.setText(applianceDetailHeater.brand);
                HeaterDays.setText(applianceDetailHeater.inputPower);
                HeaterModel.setText(applianceDetailHeater.model);
                HeaterPower.setText(applianceDetailHeater.inputPower);
                HeaterRoomNo.setText(applianceDetailHeater.roomNo);
                HeaterWeight.setText(applianceDetailHeater.weight);


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


        first.setText(applianceDetailHeater.getRoomNo());
        second.setText(applianceDetailHeater.getBrand());
        third.setText(applianceDetailHeater.getInputPower());

        return listViewItemHeater;
    }

}
