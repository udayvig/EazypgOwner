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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailInduction;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class InductionDetailList extends ArrayAdapter<ApplianceDetailInduction>{

    private Activity context;
    private List<ApplianceDetailInduction> InductionList;

    public InductionDetailList(Activity context, List<ApplianceDetailInduction> InductionList) {
        super(context, R.layout.appliance_row, InductionList);

        this.context = context;
        this.InductionList = InductionList;

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Appliances/Induction");
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemInduction = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemInduction.findViewById(R.id.firstTextView);
        TextView second = listViewItemInduction.findViewById(R.id.secondTextView);
        TextView third = listViewItemInduction.findViewById(R.id.thirdTextView);

        final ApplianceDetailInduction applianceDetailInduction = InductionList.get(position);

        listViewItemInduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText InductionCompanyName, InductionDays, InductionModel, InductionNoCooktop, InductionPower, InductionRoomNo, InductionType;

                InductionCompanyName = view.findViewById(R.id.inductionCompanyNameEditText);
                InductionDays = view.findViewById(R.id.inductionDaysEditText);
                InductionModel = view.findViewById(R.id.inductionModelEditText);
                InductionNoCooktop = view.findViewById(R.id.inductionNumberCooktopEditText);
                InductionPower = view.findViewById(R.id.inductionPowerEditText);
                InductionRoomNo = view.findViewById(R.id.inductionRoomNumberEditText);
                InductionType = view.findViewById(R.id.inductionControlTypeEditText);

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
                inductionLayout.setVisibility(View.VISIBLE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Induction" + " details");

                InductionCompanyName.setText(applianceDetailInduction.brand);
                InductionDays.setText(applianceDetailInduction.timeSinceInstallation);
                InductionModel.setText(applianceDetailInduction.model);
                InductionNoCooktop.setText(applianceDetailInduction.noOfCooktops);
                InductionPower.setText(applianceDetailInduction.inputPower);
                InductionRoomNo.setText(applianceDetailInduction.roomNo);
                InductionType.setText(applianceDetailInduction.controlType);

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

        first.setText(applianceDetailInduction.getRoomNo());
        second.setText(applianceDetailInduction.getBrand());
        third.setText(applianceDetailInduction.getTimeSinceInstallation());


        return listViewItemInduction;
    }

}
