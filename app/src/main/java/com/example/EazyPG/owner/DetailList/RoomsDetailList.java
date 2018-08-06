package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.ACDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.CCTVDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.D2HDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.DishwasherDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.FanDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.GeyserDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.HeaterDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.InductionDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.IronDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.MicrowaveDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.OtherApplianceDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.RODetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.RefrigeratorDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.RouterDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.TVDetails;
import com.example.EazyPG.owner.DetailsClasses.ApplianceDetailClasses.WashingMachineDetails;
import com.example.EazyPG.owner.DetailsClasses.RoomApplianceDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomsDetailList extends ArrayAdapter<String> {

    private Activity context;
    private List<String> roomList;
    private TextView first, second , third , fourth;
    private ListView dialogListView;
    private List<String> roomTypeList;

    private List<RoomApplianceDetails> ACList = new ArrayList<>();
    private List<RoomApplianceDetails> CCTVList = new ArrayList<>();
    private List<RoomApplianceDetails> D2HList = new ArrayList<>();
    private List<RoomApplianceDetails> dishwasherList = new ArrayList<>();
    private List<RoomApplianceDetails> fanList = new ArrayList<>();
    private List<RoomApplianceDetails> geyserList = new ArrayList<>();
    private List<RoomApplianceDetails> heaterList = new ArrayList<>();
    private List<RoomApplianceDetails> inductionList = new ArrayList<>();
    private List<RoomApplianceDetails> ironList = new ArrayList<>();
    private List<RoomApplianceDetails> microwaveList = new ArrayList<>();
    private List<RoomApplianceDetails> otherList = new ArrayList<>();
    private List<RoomApplianceDetails> refrigeratorList = new ArrayList<>();
    private List<RoomApplianceDetails> ROList = new ArrayList<>();
    private List<RoomApplianceDetails> routerList = new ArrayList<>();
    private List<RoomApplianceDetails> TVList = new ArrayList<>();
    private List<RoomApplianceDetails> wmList = new ArrayList<>();

    private List<RoomApplianceDetails> roomApplianceList = new ArrayList<>();

    private Button applianceButton, tenantButton;

    public RoomsDetailList(Activity context, List<String> roomList, List<String> roomTypeList) {
        super(context, R.layout.room_row, roomList);

        this.context = context;
        this.roomList = roomList;
        this.roomTypeList = roomTypeList;
    }

    DatabaseReference ACDatabaseReference;
    DatabaseReference CCTVDatabaseReference;
    DatabaseReference D2HDatabaseReference;
    DatabaseReference dishwasherDatabaseReference;
    DatabaseReference fanDatabaseReference;
    DatabaseReference geyserDatabaseReference;
    DatabaseReference heaterDatabaseReference;
    DatabaseReference inductionDatabaseReference;
    DatabaseReference ironDatabaseReference;
    DatabaseReference microwaveDatabaseReference;
    DatabaseReference refrigeratorDatabaseReference;
    DatabaseReference RODatabaseReference;
    DatabaseReference routerDatabaseReference;
    DatabaseReference TVDatabaseReference;
    DatabaseReference wmDatabaseReference;
    DatabaseReference otherDatabaseReference;

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater inflater = context.getLayoutInflater();
        final View listViewItemRoom = inflater.inflate(R.layout.room_row, null, true);
        final View applianceDialogLayout = inflater.inflate(R.layout.dialog_room_appliance, null, true);
        first = listViewItemRoom.findViewById(R.id.firstTextView);
        second = listViewItemRoom.findViewById(R.id.secondTextView);
        third = listViewItemRoom.findViewById(R.id.thirdTextView);
        fourth = listViewItemRoom.findViewById(R.id.fourthTextView);

        dialogListView = applianceDialogLayout.findViewById(R.id.listViewRoomAppliance);

        applianceButton = listViewItemRoom.findViewById(R.id.appliancesButton);
        tenantButton = listViewItemRoom.findViewById(R.id.tenantButton);

        third.setText(roomList.get(position));
        fourth.setText(roomTypeList.get(position));

        applianceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String room = roomList.get(position);
                ACDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/AC");
                ACDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ACList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            ACDetails acDetails = snapshot.getValue(ACDetails.class);

                            RoomApplianceDetails ac = new RoomApplianceDetails("AC", acDetails.brand, acDetails.lastServiceDate);
                            ACList.add(ac);
                        }
                        roomApplianceList.addAll(ACList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                CCTVDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/CCTV");
                CCTVDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        CCTVList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            CCTVDetails cctvDetails = snapshot.getValue(CCTVDetails.class);

                            RoomApplianceDetails cctv = new RoomApplianceDetails("CCTV", cctvDetails.brand, cctvDetails.timeSinceInstallation);
                            CCTVList.add(cctv);
                        }
                        roomApplianceList.addAll(CCTVList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                D2HDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/D2H");
                D2HDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        D2HList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            D2HDetails d2hDetails = snapshot.getValue(D2HDetails.class);

                            RoomApplianceDetails d2h = new RoomApplianceDetails("D2H", d2hDetails.brand, d2hDetails.timeSinceInstallation);
                            D2HList.add(d2h);
                        }
                        roomApplianceList.addAll(D2HList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                dishwasherDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/Dishwasher");
                dishwasherDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dishwasherList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            DishwasherDetails dishwasherDetails = snapshot.getValue(DishwasherDetails.class);

                            RoomApplianceDetails dishwasher = new RoomApplianceDetails("Dishwasher", dishwasherDetails.brand, dishwasherDetails.timeSinceInstallation);
                            dishwasherList.add(dishwasher);
                        }
                        roomApplianceList.addAll(dishwasherList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                fanDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/Fan");
                fanDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        fanList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            FanDetails fanDetails = snapshot.getValue(FanDetails.class);

                            RoomApplianceDetails fan = new RoomApplianceDetails("Fan", fanDetails.brand, fanDetails.timeSinceInstallation);
                            fanList.add(fan);
                        }
                        roomApplianceList.addAll(fanList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                geyserDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/Geyser");
                geyserDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        geyserList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            GeyserDetails geyserDetails = snapshot.getValue(GeyserDetails.class);

                            RoomApplianceDetails geyser = new RoomApplianceDetails("Geyser", geyserDetails.brand, geyserDetails.timeSinceInstallation);
                            geyserList.add(geyser);
                        }
                        roomApplianceList.addAll(geyserList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                heaterDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/Heater");
                heaterDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        heaterList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            HeaterDetails heaterDetails = snapshot.getValue(HeaterDetails.class);

                            RoomApplianceDetails heater = new RoomApplianceDetails("Heater", heaterDetails.brand, heaterDetails.timeSinceInstallation);
                            heaterList.add(heater);
                        }
                        roomApplianceList.addAll(heaterList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                inductionDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/Induction");
                inductionDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        inductionList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            InductionDetails inductionDetails = snapshot.getValue(InductionDetails.class);

                            RoomApplianceDetails induction = new RoomApplianceDetails("Induction", inductionDetails.brand, inductionDetails.timeSinceInstallation);
                            inductionList.add(induction);
                        }
                        roomApplianceList.addAll(inductionList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                ironDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/Iron");
                ironDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ironList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            IronDetails ironDetails = snapshot.getValue(IronDetails.class);

                            RoomApplianceDetails iron = new RoomApplianceDetails("Iron", ironDetails.brand, ironDetails.timeSinceInstallation);
                            ironList.add(iron);
                        }
                        roomApplianceList.addAll(ironList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                microwaveDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/Microwave");
                microwaveDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        microwaveList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            MicrowaveDetails microwaveDetails = snapshot.getValue(MicrowaveDetails.class);

                            RoomApplianceDetails microwave = new RoomApplianceDetails("Microwave", microwaveDetails.brand, microwaveDetails.timeSinceInstallation);
                            microwaveList.add(microwave);
                        }
                        roomApplianceList.addAll(microwaveList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                otherDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/Other");
                otherDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        otherList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            OtherApplianceDetails otherDetails = snapshot.getValue(OtherApplianceDetails.class);

                            RoomApplianceDetails other = new RoomApplianceDetails(otherDetails.applianceName, otherDetails.brand);
                            otherList.add(other);
                        }
                        roomApplianceList.addAll(otherList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                refrigeratorDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/Refrigerator");
                refrigeratorDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        refrigeratorList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            RefrigeratorDetails refrigeratorDetails = snapshot.getValue(RefrigeratorDetails.class);

                            RoomApplianceDetails refrigerator = new RoomApplianceDetails("Refrigerator", refrigeratorDetails.brand, refrigeratorDetails.timeSinceInstallation);
                            refrigeratorList.add(refrigerator);
                        }
                        roomApplianceList.addAll(refrigeratorList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                RODatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/RO");
                RODatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ROList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            RODetails roDetails = snapshot.getValue(RODetails.class);

                            RoomApplianceDetails ro = new RoomApplianceDetails("RO", roDetails.brand, roDetails.timeSinceInstallation);
                            ROList.add(ro);
                        }
                        roomApplianceList.addAll(ROList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                routerDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/Router");
                routerDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        routerList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            RouterDetails routerDetails = snapshot.getValue(RouterDetails.class);

                            RoomApplianceDetails router = new RoomApplianceDetails("Router", routerDetails.brand, routerDetails.timeSinceInstallation);
                            routerList.add(router);
                        }
                        roomApplianceList.addAll(routerList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                TVDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/TV");
                TVDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        TVList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            TVDetails tvDetails = snapshot.getValue(TVDetails.class);

                            RoomApplianceDetails tv = new RoomApplianceDetails("TV", tvDetails.brand, tvDetails.timeSinceInstallation);
                            TVList.add(tv);
                        }
                        roomApplianceList.addAll(TVList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                wmDatabaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/Washing Machine");
                wmDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        wmList.clear();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            WashingMachineDetails washingMachineDetails = snapshot.getValue(WashingMachineDetails.class);

                            RoomApplianceDetails wm = new RoomApplianceDetails("Washing Machine", washingMachineDetails.brand, washingMachineDetails.timeSinceInstallation);
                            wmList.add(wm);
                        }
                        roomApplianceList.addAll(wmList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //Toast.makeText(context, roomApplianceList.size() + " ", Toast.LENGTH_SHORT).show();

                RoomApplianceDialogDetailList roomApplianceDialogDetailList = new RoomApplianceDialogDetailList(context, roomApplianceList);
                dialogListView.setAdapter(roomApplianceDialogDetailList);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Appliance Details");
                builder.setPositiveButton("OK", null);
                builder.setView(applianceDialogLayout);

                builder.show();
            }
        });

        tenantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return listViewItemRoom;
    }
}
