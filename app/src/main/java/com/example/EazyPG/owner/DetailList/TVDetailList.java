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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailTV;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class TVDetailList extends ArrayAdapter<ApplianceDetailTV>{

    private Activity context;
    private List<ApplianceDetailTV> TVList;

    public TVDetailList(Activity context, List<ApplianceDetailTV> TVList) {
        super(context, R.layout.appliance_row, TVList);

        this.context = context;
        this.TVList = TVList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemTV = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemTV.findViewById(R.id.firstTextView);
        TextView second = listViewItemTV.findViewById(R.id.secondTextView);
        TextView third = listViewItemTV.findViewById(R.id.thirdTextView);

        final ApplianceDetailTV applianceDetailTV= TVList.get(position);

        listViewItemTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText TVCompanyName, TVDays, TVModel, TVResolution, TVRoomNo, TVSize, TVType;

                TVCompanyName = view.findViewById(R.id.TVCompanyNameEditText);
                TVDays = view.findViewById(R.id.TVDaysEditText);
                TVModel = view.findViewById(R.id.TVModelEditText);
                TVResolution = view.findViewById(R.id.TVResolutionEditText);
                TVRoomNo = view.findViewById(R.id.TVRoomNumberEditText);
                TVSize = view.findViewById(R.id.TVSizeEditText);
                TVType = view.findViewById(R.id.TVTypeEditText);

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
                TVLayout.setVisibility(View.VISIBLE);
                CCTVLayout.setVisibility(View.GONE);
                ironLayout.setVisibility(View.GONE);
                inductionLayout.setVisibility(View.GONE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CCTV" + " details");

                TVCompanyName.setText(applianceDetailTV.brand);
                TVDays.setText(applianceDetailTV.timeSinceInstallation);
                TVModel.setText(applianceDetailTV.model);
                TVResolution.setText(applianceDetailTV.resolution);
                TVRoomNo.setText(applianceDetailTV.roomNo);
                TVSize.setText(applianceDetailTV.screenSize);
                TVType.setText(applianceDetailTV.typeOfTV);

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


        first.setText(applianceDetailTV.getRoomNo());
        second.setText(applianceDetailTV.getBrand());
        third.setText(applianceDetailTV.getTypeOfTV());

        return listViewItemTV;
    }

}