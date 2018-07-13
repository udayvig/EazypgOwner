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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailMicrowave;
import com.example.EazyPG.owner.Appliances.MicrowaveDetails;
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

public class MicrowaveDetailList extends ArrayAdapter<ApplianceDetailMicrowave>{

    private Activity context;
    private List<ApplianceDetailMicrowave> MicrowaveList;

    public MicrowaveDetailList(Activity context, List<ApplianceDetailMicrowave> MicrowaveList) {
        super(context, R.layout.appliance_row, MicrowaveList);

        this.context = context;
        this.MicrowaveList = MicrowaveList;

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Appliances/Microwave");
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemMicrowave = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemMicrowave.findViewById(R.id.firstTextView);
        TextView second = listViewItemMicrowave.findViewById(R.id.secondTextView);
        TextView third = listViewItemMicrowave.findViewById(R.id.thirdTextView);

        final ApplianceDetailMicrowave applianceDetailMicrowave= MicrowaveList.get(position);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ids.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    MicrowaveDetails microwaveDetails = snapshot.getValue(MicrowaveDetails.class);

                    String id = microwaveDetails.id;

                    ids.add(id);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewItemMicrowave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText MicrowaveCapacity, MicrowaveCompanyName, MicrowaveDays, MicrowaveModel, MicrowaveType, MicrowaveRoomNo;

                MicrowaveCapacity = viewDialog.findViewById(R.id.microwaveCapacityEditText);
                MicrowaveCompanyName = viewDialog.findViewById(R.id.microwaveCompanyNameEditText);
                MicrowaveDays = viewDialog.findViewById(R.id.microwaveDaysEditText);
                MicrowaveModel = viewDialog.findViewById(R.id.microwaveModelEditText);
                MicrowaveType = viewDialog.findViewById(R.id.microwaveTypeEditText);
                MicrowaveRoomNo = viewDialog.findViewById(R.id.microwaveRoomNumberEditText);

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
                microwaveLayout.setVisibility(View.VISIBLE);
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
                builder.setTitle("CCTV" + " details");

                MicrowaveCapacity.setText(applianceDetailMicrowave.capacity);
                MicrowaveCompanyName.setText(applianceDetailMicrowave.brand);
                MicrowaveDays.setText(applianceDetailMicrowave.timeSinceInstallation);
                MicrowaveModel.setText(applianceDetailMicrowave.model);
                MicrowaveRoomNo.setText(applianceDetailMicrowave.roomNo);
                MicrowaveType.setText(applianceDetailMicrowave.controlType);

                builder.setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Saving...", true);

                        String capacityMicrowave = MicrowaveCapacity.getText().toString();
                        String brandMicrowave = MicrowaveCompanyName.getText().toString();
                        String daysMicrowave = MicrowaveDays.getText().toString();
                        String modelMicrowave = MicrowaveModel.getText().toString();
                        String typeMicrowave = MicrowaveType.getText().toString();
                        String roomNoMicrowave = MicrowaveRoomNo.getText().toString();
                        String uidMicrowave = ids.get(position);

                        if (brandMicrowave.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }

                        else {
                            MicrowaveDetails microwaveDetails = new MicrowaveDetails(uidMicrowave, roomNoMicrowave, brandMicrowave, modelMicrowave, daysMicrowave, capacityMicrowave, typeMicrowave);
                            databaseReference.child(uidMicrowave).setValue(microwaveDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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


        first.setText(applianceDetailMicrowave.getRoomNo());
        second.setText(applianceDetailMicrowave.getBrand());
        third.setText(applianceDetailMicrowave.getCapacity());

        return listViewItemMicrowave;
    }

}
