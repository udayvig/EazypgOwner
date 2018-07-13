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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailIron;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class IronDetailList extends ArrayAdapter<ApplianceDetailIron>{

    private Activity context;
    private List<ApplianceDetailIron> IronList;

    public IronDetailList(Activity context, List<ApplianceDetailIron> IronList) {
        super(context, R.layout.appliance_row, IronList);

        this.context = context;
        this.IronList = IronList;

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Appliances/Iron");
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemIron = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemIron.findViewById(R.id.firstTextView);
        TextView second = listViewItemIron.findViewById(R.id.secondTextView);
        TextView third = listViewItemIron.findViewById(R.id.thirdTextView);

        final ApplianceDetailIron applianceDetailIron = IronList.get(position);

        listViewItemIron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText IronComanyName, IronDays, IronModel, IronPower, IronRoomNo;

                IronComanyName = view.findViewById(R.id.ironCompanyNameEditText);
                IronDays = view.findViewById(R.id.ironDaysEditText);
                IronModel = view.findViewById(R.id.ironModelEditText);
                IronPower = view.findViewById(R.id.ironPowerEditText);
                IronRoomNo = view.findViewById(R.id.ironRoomNumberEditText);

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
                ironLayout.setVisibility(View.VISIBLE);
                inductionLayout.setVisibility(View.GONE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CCTV" + " details");

                IronComanyName.setText(applianceDetailIron.brand);
                IronDays.setText(applianceDetailIron.timeSinceInstallation);
                IronModel.setText(applianceDetailIron.model);
                IronPower.setText(applianceDetailIron.inputPower);
                IronRoomNo.setText(applianceDetailIron.roomNo);

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


        first.setText(applianceDetailIron.getRoomNo());
        second.setText(applianceDetailIron.getBrand());
        third.setText(applianceDetailIron.getTimeSinceInstallation());

        return listViewItemIron;
    }

}
