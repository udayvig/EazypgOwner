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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailRefrigerator;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class RefrigeratorDetailList extends ArrayAdapter<ApplianceDetailRefrigerator>{

    private Activity context;
    private List<ApplianceDetailRefrigerator> RefrigeratorList;

    public RefrigeratorDetailList(Activity context, List<ApplianceDetailRefrigerator> RefrigeratorList) {
        super(context, R.layout.appliance_row, RefrigeratorList);

        this.context = context;
        this.RefrigeratorList = RefrigeratorList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemRefrigerator = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemRefrigerator.findViewById(R.id.firstTextView);
        TextView second = listViewItemRefrigerator.findViewById(R.id.secondTextView);
        TextView third = listViewItemRefrigerator.findViewById(R.id.thirdTextView);

        final ApplianceDetailRefrigerator applianceDetailRefrigerator= RefrigeratorList.get(position);

        listViewItemRefrigerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText FridgeCapacity, FridgeCompanyName,  FridgeDays,  FridgeModel,  FridgeRoomNo,  FridgeType, FridgeRating;

                FridgeCapacity = view.findViewById(R.id.fridgeCapacityEditText);
                FridgeCompanyName = view.findViewById(R.id.fridgeCompanyNameEditText);
                FridgeDays = view.findViewById(R.id.fridgeDaysEditText);
                FridgeModel = view.findViewById(R.id.fridgeModelEditText);
                FridgeRoomNo = view.findViewById(R.id.fridgeRoomNumberEditText);
                FridgeType = view.findViewById(R.id.fridgeTypeEditText);
                FridgeRating = view.findViewById(R.id.fridgeRatingEditText);

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
                fridgeLayout.setVisibility(View.VISIBLE);
                TVLayout.setVisibility(View.GONE);
                CCTVLayout.setVisibility(View.GONE);
                ironLayout.setVisibility(View.GONE);
                inductionLayout.setVisibility(View.GONE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Refrigeraator" + " details");

                FridgeCapacity.setText(applianceDetailRefrigerator.capacity);
                FridgeCompanyName.setText(applianceDetailRefrigerator.brand);
                FridgeDays.setText(applianceDetailRefrigerator.timeSinceInstallation);
                FridgeModel.setText(applianceDetailRefrigerator.model);
                FridgeRating.setText(applianceDetailRefrigerator.starRating);
                FridgeRoomNo.setText(applianceDetailRefrigerator.roomNo);
                FridgeType.setText(applianceDetailRefrigerator.typeOfRefrigerator);

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


        first.setText(applianceDetailRefrigerator.getRoomNo());
        second.setText(applianceDetailRefrigerator.getBrand());
        third.setText(applianceDetailRefrigerator.getTypeOfRefrigerator());

        return listViewItemRefrigerator;
    }

}
