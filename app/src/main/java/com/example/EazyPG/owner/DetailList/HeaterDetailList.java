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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailHeater;
import com.example.EazyPG.owner.Appliances.HeaterDetails;
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

public class HeaterDetailList extends ArrayAdapter<ApplianceDetailHeater>{

    private Activity context;
    private List<ApplianceDetailHeater> HeaterList;

    public HeaterDetailList(Activity context, List<ApplianceDetailHeater> HeaterList) {
        super(context, R.layout.appliance_row, HeaterList);

        this.context = context;
        this.HeaterList = HeaterList;

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Appliances/Heater");
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemHeater = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemHeater.findViewById(R.id.firstTextView);
        TextView second = listViewItemHeater.findViewById(R.id.secondTextView);
        TextView third = listViewItemHeater.findViewById(R.id.thirdTextView);

        final ApplianceDetailHeater applianceDetailHeater= HeaterList.get(position);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ids.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    HeaterDetails heaterDetails = snapshot.getValue(HeaterDetails.class);

                    String id = heaterDetails.id;

                    ids.add(id);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewItemHeater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText HeaterCompanyName, HeaterDays, HeaterModel, HeaterPower, HeaterRoomNo,HeaterWeight;

                HeaterCompanyName = view.findViewById(R.id.heaterCompanyNameEditText);
                HeaterDays = view.findViewById(R.id.heaterDaysEditText);
                HeaterModel = view.findViewById(R.id.heaterModelEditText);
                HeaterPower = view.findViewById(R.id.heaterPowerEditText);
                HeaterRoomNo = view.findViewById(R.id.heaterRoomNumberEditText);
                HeaterWeight = view.findViewById(R.id.heaterWeightEditText);

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
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.VISIBLE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Heater" + " details");

                HeaterCompanyName.setText(applianceDetailHeater.brand);
                HeaterDays.setText(applianceDetailHeater.inputPower);
                HeaterModel.setText(applianceDetailHeater.model);
                HeaterPower.setText(applianceDetailHeater.inputPower);
                HeaterRoomNo.setText(applianceDetailHeater.roomNo);
                HeaterWeight.setText(applianceDetailHeater.weight);


                builder.setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Saving...", true);

                        String brandHeater = HeaterCompanyName.getText().toString();
                        String daysHeater = HeaterDays.getText().toString();
                        String modelHeater = HeaterModel.getText().toString();
                        String powerHeater = HeaterPower.getText().toString();
                        String roomNoHeater = HeaterRoomNo.getText().toString();
                        String weightHeater = HeaterWeight.getText().toString();
                        String uidHeater = ids.get(position);

                        if (roomNoHeater.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }

                        else {

                            HeaterDetails heaterDetails = new HeaterDetails(uidHeater, roomNoHeater, brandHeater, modelHeater, daysHeater, powerHeater, weightHeater);
                            databaseReference.child(uidHeater).setValue(heaterDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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


        first.setText(applianceDetailHeater.getRoomNo());
        second.setText(applianceDetailHeater.getBrand());
        third.setText(applianceDetailHeater.getInputPower());

        return listViewItemHeater;
    }

}
