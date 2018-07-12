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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailWashingMachine;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class WashingMachineDetailList extends ArrayAdapter<ApplianceDetailWashingMachine>{

    private Activity context;
    private List<ApplianceDetailWashingMachine> washingMachineList;

    public WashingMachineDetailList(Activity context, List<ApplianceDetailWashingMachine> washingMachineList) {
        super(context, R.layout.appliance_row, washingMachineList);

        this.context = context;
        this.washingMachineList = washingMachineList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemWashingMachine = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemWashingMachine.findViewById(R.id.firstTextView);
        TextView second = listViewItemWashingMachine.findViewById(R.id.secondTextView);
        TextView third = listViewItemWashingMachine.findViewById(R.id.thirdTextView);

        final ApplianceDetailWashingMachine applianceDetailWashingMachine = washingMachineList.get(position);

        listViewItemWashingMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText WashingMachineRoomNo, WashingMachineCompanyName, WashingMachineModel, WashingMachineDays, WashingMachineCapacity, WashingMachinePower, WashingMachineRating, WashingMachineType;

                WashingMachineRoomNo = view.findViewById(R.id.wmRoomNumberEditText);
                WashingMachineCompanyName = view.findViewById(R.id.wmCompanyNameEditText);
                WashingMachineModel = view.findViewById(R.id.wmModelEditText);
                WashingMachineDays = view.findViewById(R.id.wmDaysEditText);
                WashingMachineCapacity = view.findViewById(R.id.wmCapacityEditText);
                WashingMachinePower = view.findViewById(R.id.wmPowerEditText);
                WashingMachineRating = view.findViewById(R.id.wmRatingEditText);
                WashingMachineType = view.findViewById(R.id.wmTypeEditText);

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
                washingMachineLayout.setVisibility(View.VISIBLE);
                ROLayout.setVisibility(View.GONE);
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
                builder.setTitle("Washine Machine" + " details");

                WashingMachineCapacity.setText(applianceDetailWashingMachine.capacity);
                WashingMachineCompanyName.setText(applianceDetailWashingMachine.brand);
                WashingMachineDays.setText(applianceDetailWashingMachine.timeSinceInstallation);
                WashingMachineModel.setText(applianceDetailWashingMachine.model);
                WashingMachinePower.setText(applianceDetailWashingMachine.inputPower);
                WashingMachineRating.setText(applianceDetailWashingMachine.starRating);
                WashingMachineRoomNo.setText(applianceDetailWashingMachine.roomNo);
                WashingMachineType.setText(applianceDetailWashingMachine.typeOfMachine);

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


        first.setText(applianceDetailWashingMachine.getRoomNo());
        second.setText(applianceDetailWashingMachine.getBrand());
        third.setText(applianceDetailWashingMachine.getTypeOfMachine());

        return listViewItemWashingMachine;
    }

}
