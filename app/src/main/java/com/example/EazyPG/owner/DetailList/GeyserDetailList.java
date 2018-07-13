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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailGeyser;
import com.example.EazyPG.owner.Appliances.ACDetails;
import com.example.EazyPG.owner.Appliances.GeyserDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GeyserDetailList extends ArrayAdapter<ApplianceDetailGeyser>{

    private Activity context;
    private List<ApplianceDetailGeyser> geyserList;

    public GeyserDetailList(Activity context, List<ApplianceDetailGeyser> geyserList) {
        super(context, R.layout.appliance_row, geyserList);

        this.context = context;
        this.geyserList = geyserList;

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Appliances/Geyser");
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemGeyser = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemGeyser.findViewById(R.id.firstTextView);
        TextView second = listViewItemGeyser.findViewById(R.id.secondTextView);
        TextView third = listViewItemGeyser.findViewById(R.id.thirdTextView);

        final ApplianceDetailGeyser applianceDetailGeyser = geyserList.get(position);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ids.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    GeyserDetails geyserDetails = snapshot.getValue(GeyserDetails.class);

                    String id = geyserDetails.id;

                    ids.add(id);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewItemGeyser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText GeyserRoomNo, GeyserCompanyName, GeyserModel, GeyserDays, GeyserCapacity, GeyserPower, GeyserRating;

                GeyserRoomNo = view.findViewById(R.id.geyserRoomNumberEditText);
                GeyserCompanyName = view.findViewById(R.id.geyserCompanyNameEditText);
                GeyserModel = view.findViewById(R.id.geyserModelEditText);
                GeyserDays = view.findViewById(R.id.geyserDaysEditText);
                GeyserCapacity = view.findViewById(R.id.geyserCapacityEditText);
                GeyserPower = view.findViewById(R.id.geyserPowerEditText);
                GeyserRating = view.findViewById(R.id.geyserRatingEditText);

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
                geyserLayout.setVisibility(View.VISIBLE);
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
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Geyser" + " details");

                GeyserCapacity.setText(applianceDetailGeyser.capacity);
                GeyserCompanyName.setText(applianceDetailGeyser.brand);
                GeyserDays.setText(applianceDetailGeyser.timeSinceInstallation);
                GeyserModel.setText(applianceDetailGeyser.model);
                GeyserPower.setText(applianceDetailGeyser.inputPower);
                GeyserRating.setText(applianceDetailGeyser.starRating);
                GeyserRoomNo.setText(applianceDetailGeyser.roomNo);


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

        first.setText(applianceDetailGeyser.getRoomNo());
        second.setText(applianceDetailGeyser.getBrand());
        third.setText(applianceDetailGeyser.getTimeSinceInstallation());

        return listViewItemGeyser;
    }


}
