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

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailDishwasher;
import com.example.EazyPG.owner.Appliances.ACDetails;
import com.example.EazyPG.owner.Appliances.DishwasherDetails;
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

public class DishwasherDetailList extends ArrayAdapter<ApplianceDetailDishwasher>{

    private Activity context;
    private List<ApplianceDetailDishwasher> DishwasherList;

    public DishwasherDetailList(Activity context, List<ApplianceDetailDishwasher> DishwasherList) {
        super(context, R.layout.appliance_row, DishwasherList);

        this.context = context;
        this.DishwasherList = DishwasherList;

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Appliances/Dishwasher");
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemDishwasher = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemDishwasher.findViewById(R.id.firstTextView);
        TextView second = listViewItemDishwasher.findViewById(R.id.secondTextView);
        TextView third = listViewItemDishwasher.findViewById(R.id.thirdTextView);

        final ApplianceDetailDishwasher applianceDetailDishwasher= DishwasherList.get(position);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ids.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    DishwasherDetails dishwasherDetails = snapshot.getValue(DishwasherDetails.class);

                    String id = dishwasherDetails.id;

                    ids.add(id);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewItemDishwasher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText DishwasherCompanyName,DishwasherDays,DishwasherCapacity, DishwasherModel, DishwasherRoomNo, DishwasherType;

                DishwasherCapacity = viewDialog.findViewById(R.id.dishwasherCapacityEditText);
                DishwasherCompanyName = viewDialog.findViewById(R.id.dishwasherCompanyNameEditText);
                DishwasherDays = viewDialog.findViewById(R.id.dishwasherDaysEditText);
                DishwasherModel = viewDialog.findViewById(R.id.dishwasherModelEditText);
                DishwasherRoomNo = viewDialog.findViewById(R.id.dishwasherRoomNumberEditText);
                DishwasherType = viewDialog.findViewById(R.id.dishwasherTypeEditText);

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
                dishwasherLayout.setVisibility(View.VISIBLE);
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
                builder.setTitle("Dishwasher" + " details");

                DishwasherCapacity.setText(applianceDetailDishwasher.capacity);
                DishwasherCompanyName.setText(applianceDetailDishwasher.brand);
                DishwasherDays.setText(applianceDetailDishwasher.timeSinceInstallation);
                DishwasherModel.setText(applianceDetailDishwasher.model);
                DishwasherRoomNo.setText(applianceDetailDishwasher.roomNo);
                DishwasherType.setText(applianceDetailDishwasher.typeOfDishwasher);

                builder.setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Saving...", true);

                        String capacityDishwasher = DishwasherCapacity.getText().toString();
                        String brandDishwasher = DishwasherCompanyName.getText().toString();
                        String daysDishwasher = DishwasherDays.getText().toString();
                        String modelDishwasher = DishwasherModel.getText().toString();
                        String roomNoDishwasher = DishwasherRoomNo.getText().toString();
                        String typeDishwasher = DishwasherType.getText().toString();
                        String uidDishwasher = ids.get(position);

                        if (brandDishwasher.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                        else {
                            DishwasherDetails dishwasherDetails = new DishwasherDetails(uidDishwasher, roomNoDishwasher, brandDishwasher, modelDishwasher, daysDishwasher, capacityDishwasher, typeDishwasher);
                            databaseReference.child(uidDishwasher).setValue(dishwasherDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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


        first.setText(applianceDetailDishwasher.getRoomNo());
        second.setText(applianceDetailDishwasher.getBrand());
        third.setText(applianceDetailDishwasher.getTimeSinceInstallation());

        return listViewItemDishwasher;
    }

}
