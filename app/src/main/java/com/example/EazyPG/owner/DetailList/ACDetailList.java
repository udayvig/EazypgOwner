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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailAC;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class ACDetailList extends ArrayAdapter<ApplianceDetailAC>{

    private Activity context;
    private List<ApplianceDetailAC> acList;

    public ACDetailList(Activity context, List<ApplianceDetailAC> acList) {
        super(context, R.layout.appliance_row, acList);

        this.context = context;
        this.acList = acList;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemAC = inflater.inflate(R.layout.appliance_row, null, true);
        final ApplianceDetailAC applianceDetailAC = acList.get(position);

        listViewItemAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);

                final EditText ACRoomNo,ACCompanyName,ACModel,ACCapacity,ACLastServiceDate,ACStarRating,ACType;
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

                ACLayout.setVisibility(View.VISIBLE);
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
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("AC" + " details");

                ACRoomNo = viewDialog.findViewById(R.id.ACRoomNumberEditText);
                ACCompanyName = viewDialog.findViewById(R.id.ACCompanyNameEditText);
                ACModel = viewDialog.findViewById(R.id.ACModelEditText);
                ACCapacity = viewDialog.findViewById(R.id.ACCapacityEditText);
                ACLastServiceDate = viewDialog.findViewById(R.id.ACLastServiceDateEditText);
                ACStarRating = viewDialog.findViewById(R.id.ACStarRatingEditText);
                ACType = viewDialog.findViewById(R.id.ACTypeEditText);

                ACRoomNo.setText(applianceDetailAC.roomNo);
                ACCompanyName.setText(applianceDetailAC.brand);
                ACModel.setText(applianceDetailAC.model);
                ACCapacity.setText(applianceDetailAC.capacity);
                ACLastServiceDate.setText(applianceDetailAC.lastServiceDate);
                ACStarRating.setText(applianceDetailAC.starRating);
                ACType.setText(applianceDetailAC.typeOfRating);

                builder.setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       /* FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(user.getUid() + "/Appliances/AC");

                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshotAC : dataSnapshot.getChildren()) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });*/

                       /* ApplianceDetailAC ac = new ApplianceDetailAC(ACRoomNo.getText().toString(), ACCompanyName.getText().toString(),
                                ACModel.getText().toString(), ACCapacity.getText().toString(), ACLastServiceDate.getText().toString(),
                                ACStarRating.getText().toString(), ACType.getText().toString());
                        databaseReference.child("Appliances").child("AC").child(neededUid).setValue(ac).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show();
                            }
                        });*/
                    }
                });

                builder.setNegativeButton("Cancel",null);

                builder.show();
            }
        });

        TextView first = listViewItemAC.findViewById(R.id.firstTextView);
        TextView second = listViewItemAC.findViewById(R.id.secondTextView);
        TextView third = listViewItemAC.findViewById(R.id.thirdTextView);

        first.setText(applianceDetailAC.getRoomNo());
        second.setText(applianceDetailAC.getBrand());
        third.setText(applianceDetailAC.getLastServiceDate());

        return listViewItemAC;
    }


}
