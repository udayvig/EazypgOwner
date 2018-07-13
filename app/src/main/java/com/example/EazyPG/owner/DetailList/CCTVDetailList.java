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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailCCTV;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CCTVDetailList extends ArrayAdapter<ApplianceDetailCCTV>{

    private Activity context;
    private List<ApplianceDetailCCTV> CCTVList;

    public CCTVDetailList(Activity context, List<ApplianceDetailCCTV> CCTVList) {
        super(context, R.layout.appliance_row, CCTVList);

        this.context = context;
        this.CCTVList = CCTVList;

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Appliances/CCTV");
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemCCTV = inflater.inflate(R.layout.appliance_row, null, true);

        final ApplianceDetailCCTV applianceDetailCCTV = CCTVList.get(position);

        listViewItemCCTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);

                EditText CCTVChanel,CCTVCompanyName,CCTVDays,CCTVModel,CCTVNight,CCTVResolution,CCTVRoomNo;
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
                CCTVLayout.setVisibility(View.VISIBLE);
                ironLayout.setVisibility(View.GONE);
                inductionLayout.setVisibility(View.GONE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                CCTVChanel = viewDialog.findViewById(R.id.CCTVChannelEditText);
                CCTVCompanyName = viewDialog.findViewById(R.id.CCTVCompanyNameEditText);
                CCTVDays = viewDialog.findViewById(R.id.CCTVDaysEditText);
                CCTVModel = viewDialog.findViewById(R.id.CCTVModelEditText);
                CCTVNight = viewDialog.findViewById(R.id.CCTVNightEditText);
                CCTVResolution = viewDialog.findViewById(R.id.CCTVResolutionEditText);
                CCTVRoomNo = viewDialog.findViewById(R.id.CCTVRoomNumberEditText);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CCTV" + " details");

                CCTVRoomNo.setText(applianceDetailCCTV.roomNo);
                CCTVCompanyName.setText(applianceDetailCCTV.brand);
                CCTVModel.setText(applianceDetailCCTV.model);
                CCTVChanel.setText(applianceDetailCCTV.noOfChannels);
                CCTVDays.setText(applianceDetailCCTV.timeSinceInstallation);
                CCTVResolution.setText(applianceDetailCCTV.recordingResolution);
                CCTVNight.setText(applianceDetailCCTV.nightVision);

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

        TextView first = listViewItemCCTV.findViewById(R.id.firstTextView);
        TextView second = listViewItemCCTV.findViewById(R.id.secondTextView);
        TextView third = listViewItemCCTV.findViewById(R.id.thirdTextView);

        first.setText(applianceDetailCCTV.getRoomNo());
        second.setText(applianceDetailCCTV.getBrand());
        third.setText(applianceDetailCCTV.getTimeSinceInstallation());

        return listViewItemCCTV;
    }

}
