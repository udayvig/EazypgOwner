package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailRouter;
import com.example.EazyPG.owner.Appliances.RouterDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemRouter = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemRouter.findViewById(R.id.firstTextView);
        TextView second = listViewItemRouter.findViewById(R.id.secondTextView);
        TextView third = listViewItemRouter.findViewById(R.id.thirdTextView);

        final ApplianceDetailRouter applianceDetailRouter = RouterList.get(position);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ids.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    RouterDetails routerDetails = snapshot.getValue(RouterDetails.class);

                    String id = routerDetails.id;

                    ids.add(id);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewItemRouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText RouterAntenna, RouterCompanyName, RouterDays, RouterModel, RouterRoomNo, RouterSpeed;

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

                        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Saving...", true);

                        String antennaRouter = RouterAntenna.getText().toString();
                        String brandRouter = RouterCompanyName.getText().toString();
                        String daysRouter = RouterDays.getText().toString();
                        String modelRouter = RouterModel.getText().toString();
                        String roomNoRouter = RouterRoomNo.getText().toString();
                        String speedRouter = RouterSpeed.getText().toString();
                        String uidRouter = ids.get(position);

                        if (daysRouter.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else {

                            RouterDetails routerDetails = new RouterDetails(uidRouter, roomNoRouter, brandRouter, modelRouter, daysRouter, antennaRouter, speedRouter);
                            databaseReference.child(uidRouter).setValue(routerDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
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
