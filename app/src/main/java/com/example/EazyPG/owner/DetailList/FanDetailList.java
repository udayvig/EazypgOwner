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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailFan;
import com.example.EazyPG.owner.Appliances.ACDetails;
import com.example.EazyPG.owner.Appliances.FanDetails;
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

public class FanDetailList extends ArrayAdapter<ApplianceDetailFan> {

    private Activity context;
    private List<ApplianceDetailFan> fanList;

    public FanDetailList(Activity context, List<ApplianceDetailFan> fanList) {
        super(context, R.layout.appliance_row, fanList);

        this.context = context;
        this.fanList = fanList;

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Appliances/Fan");
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemFan = inflater.inflate(R.layout.appliance_row, null, true);
        final ApplianceDetailFan applianceDetailFan = fanList.get(position);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ids.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    FanDetails fanDetails = snapshot.getValue(FanDetails.class);

                    String id = fanDetails.id;

                    ids.add(id);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewItemFan.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Delete Item");
                builder.setMessage("Are you sure you want to delete item?");
                builder.setIcon(R.drawable.ic_warning_black_24dp);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Deleting...", true);

                        String id = ids.get(position);

                        databaseReference.child(id).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                });

                builder.setNegativeButton("Cancel", null);
                builder.show();

                return true;
            }
        });


        listViewItemFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);

                final EditText FanRoomNo, FanCompanyName, FanModel, FanDays, FanBlades;

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
                fanLayout.setVisibility(View.VISIBLE);
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
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Fan" + " details");

                FanRoomNo = viewDialog.findViewById(R.id.fanRoomNumberEditText);
                FanCompanyName = viewDialog.findViewById(R.id.fanCompanyNameEditText);
                FanModel = viewDialog.findViewById(R.id.fanModelEditText);
                FanDays = viewDialog.findViewById(R.id.fanDaysEditText);
                FanBlades = viewDialog.findViewById(R.id.fanBladesEditText);

                FanBlades.setText(applianceDetailFan.noOfBlades);
                FanCompanyName.setText(applianceDetailFan.brand);
                FanDays.setText(applianceDetailFan.timeSinceInstallation);
                FanModel.setText(applianceDetailFan.model);
                FanRoomNo.setText(applianceDetailFan.roomNo);

                builder.setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Saving...", true);

                        String roomNoFan = FanRoomNo.getText().toString();
                        String brandFan = FanCompanyName.getText().toString();
                        String modelFan = FanModel.getText().toString();
                        String timeSinceInstallationFan = FanDays.getText().toString();
                        String noOfBladesFan = FanBlades.getText().toString();
                        String uidFan = ids.get(position);

                        if (roomNoFan.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        } else {

                            FanDetails fanDetails = new FanDetails(uidFan, roomNoFan, brandFan, modelFan, timeSinceInstallationFan, noOfBladesFan);
                            databaseReference.child(uidFan).setValue(fanDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

        TextView first = listViewItemFan.findViewById(R.id.firstTextView);
        TextView second = listViewItemFan.findViewById(R.id.secondTextView);
        TextView third = listViewItemFan.findViewById(R.id.thirdTextView);

        first.setText(applianceDetailFan.getRoomNo());
        second.setText(applianceDetailFan.getBrand());
        third.setText(applianceDetailFan.getTimeSinceInstallation());

        return listViewItemFan;
    }


}

