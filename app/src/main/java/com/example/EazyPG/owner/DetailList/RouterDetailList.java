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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailRouter;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RouterDetailList extends ArrayAdapter<ApplianceDetailRouter>{

    private Activity context;
    private List<ApplianceDetailRouter> RouterList;

    public RouterDetailList(Activity context, List<ApplianceDetailRouter> RouterList) {
        super(context, R.layout.appliance_row, RouterList);

        this.context = context;
        this.RouterList = RouterList;

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Appliances/Router");
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemRouter = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemRouter.findViewById(R.id.firstTextView);
        TextView second = listViewItemRouter.findViewById(R.id.secondTextView);
        TextView third = listViewItemRouter.findViewById(R.id.thirdTextView);

        final ApplianceDetailRouter applianceDetailRouter = RouterList.get(position);

        listViewItemRouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText RouterAntenna, RouterCompanyName, RouterDays, RouterModel, RouterRoomNo, RouterSpeed;

                RouterAntenna = view.findViewById(R.id.routerAntennaEditText);
                RouterCompanyName = view.findViewById(R.id.routerCompanyNameEditText);
                RouterDays = view.findViewById(R.id.routerDaysEditText);
                RouterModel = view.findViewById(R.id.routerModelEditText);
                RouterRoomNo = view.findViewById(R.id.routerRoomNumberEditText);
                RouterSpeed = view.findViewById(R.id.routerSpeedEditText);

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
                routerLayout.setVisibility(View.VISIBLE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CCTV" + " details");

                RouterAntenna.setText(applianceDetailRouter.noOfAntenna);
                RouterCompanyName.setText(applianceDetailRouter.brand);
                RouterDays.setText(applianceDetailRouter.timeSinceInstallation);
                RouterModel.setText(applianceDetailRouter.model);
                RouterRoomNo.setText(applianceDetailRouter.roomNo);
                RouterSpeed.setText(applianceDetailRouter.wirelessSpeed);

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


        first.setText(applianceDetailRouter.getRoomNo());
        second.setText(applianceDetailRouter.getBrand());
        third.setText(applianceDetailRouter.getTimeSinceInstallation());

        return listViewItemRouter;
    }

}
