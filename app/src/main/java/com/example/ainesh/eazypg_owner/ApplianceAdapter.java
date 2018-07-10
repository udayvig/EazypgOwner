package com.example.ainesh.eazypg_owner;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Uday Vig on 09-Jul-18.
 */

public class ApplianceAdapter extends RecyclerView.Adapter<ApplianceAdapter.MyViewHolder>{

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    private RelativeLayout ACLayout, fanLayout, liftLayout, geyserLayout, washingMachineLayout, ROLayout, dishwasherLayout, microwaveLayout,
            fridgeLayout, TVLayout, CCTVLayout, ironLayout, inductionLayout, routerLayout, heaterLayout, D2HLayout, otherLayout;

    private EditText ACRoomNo, ACCompanyName, ACModel, ACCapacity, ACLastServiceDate, ACStarRating, ACType;
    private EditText FanRoomNo, FanCompanyName, FanModel, FanDays, FanBlades;
    private EditText LiftCompanyName, LiftModel, LiftDays, LiftCapacity, LiftDoor;
    private EditText GeyserRoomNo, GeyserCompanyName, GeyserModel, GeyserDays, GeyserCapacity, GeyserPower, GeyserRating;
    private EditText WashingMachineRoomNo, WashingMachineCompanyName, WashingMachineModel, WashingMachineDays, WashingMachineCapacity, WashingMachinePower, WashingMachineRating, WashingMachineType;
    private EditText RORoomNo, ROCompanyName, ROModel, RODays, ROCapacity;
    private EditText DishwasherRoomNo, DishwasherCompanyName, DishwasherModel, DishwasherDays, DishwasherCapacity, DishwasherType;
    private EditText MicrowaveRoomNo, MicrowaveCompanyName, MicrowaveModel, MicrowaveDays, MicrowaveCapacity, MicrowaveType;
    private EditText FridgeRoomNo, FridgeCompanyName, FridgeModel, FridgeDays, FridgeCapacity, FridgeType, FridgeRating;
    private EditText TVRoomNo, TVCompanyName, TVModel, TVDays, TVType, TVSize, TVResolution;
    private EditText CCTVRoomNo, CCTVCompanyName, CCTVModel, CCTVDays, CCTVNight, CCTVChanel, CCTVResolution;
    private EditText IronRoomNo, IronComanyName, IronModel, IronDays, IronPower;
    private EditText InductionRoomNo, InductionCompanyName, InductionModel, InductionDays, InductionPower, InductionControl, InductionType, InductionNoCooktop;
    private EditText RouterRoomNo, RouterCompanyName, RouterModel, RouterDays, RouterAntenna, RouterSpeed;
    private EditText HeaterRoomNo, HeaterCompanyName, HeaterModel, HeaterDays, HeaterPower, HeaterWeight;
    private EditText D2HRoomNo, D2HCompanyName, D2HDays;
    private EditText OtherRoomNo, OtherName, OtherCompanyName;

    //Interface
    public interface ClickListener {
        void onPositionClicked(int position);
    }

    private LayoutInflater inflater;
    private final ClickListener clickListener;

    ApplianceAdapter(String[] app, LayoutInflater inflater, ClickListener listener){
        this.clickListener=listener;
        this.inflater=inflater;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.appliance_item, parent,false), clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return 17;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public String[] appliances={"AC","Fan","Lift","Geyser","Washing Machine",
                "RO","Dishwasher","Microwave","Refrigerator","TV","CCTV","Iron",
                "Induction","Router","Heater","D2H","Other"};
        private TextView iconTextView;
        private Button iconButton;


        public MyViewHolder(final View itemView, ClickListener listener){
            super(itemView);

            iconTextView=itemView.findViewById(R.id.iconTextView);
            iconButton=itemView.findViewById(R.id.iconButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Whole Item Clicked.", Toast.LENGTH_SHORT).show();
        }

        public void bind(MyViewHolder holder, int index){
            iconTextView.setText(appliances[index]);

            holder.iconButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    databaseReference = firebaseDatabase.getReference(firebaseUser.getUid());

                    if (iconTextView.getText().toString().equals("AC")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add AC", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Fan")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Fan", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Lift")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Lift", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Geyser")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Geyser", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Washing Machine")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Washing Machine", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("RO")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add RO", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Dishwasher")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Dish Washer", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Microwave")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Microwave", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Refrigerator")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Refrigerator", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("TV")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add TV", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("CCTV")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add CCTV", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Iron")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Iron", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Induction")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Induction", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Router")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Router", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Heater")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Heater", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("D2H")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add D2H", Toast.LENGTH_SHORT).show();
                    }
                    else if (iconTextView.getText().toString().equals("Other")) {
                        showDialog(iconTextView.getText().toString(), view.getContext());
                        Toast.makeText(view.getContext(), "Add Other", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void showDialog(final String applianceName, Context context) {

        final View view = inflater.inflate(R.layout.dialog_appliance, null);

        ACLayout = view.findViewById(R.id.ACLayout);
        fanLayout = view.findViewById(R.id.fanLayout);
        liftLayout = view.findViewById(R.id.liftLayout);
        geyserLayout = view.findViewById(R.id.geyserLayout);
        washingMachineLayout = view.findViewById(R.id.washingMachineLayout);
        ROLayout = view.findViewById(R.id.ROLayout);
        dishwasherLayout = view.findViewById(R.id.dishwasherLayout);
        microwaveLayout = view.findViewById(R.id.microwaveLayout);
        fridgeLayout = view.findViewById(R.id.fridgeLayout);
        TVLayout = view.findViewById(R.id.TVLayout);
        CCTVLayout = view.findViewById(R.id.CCTVLayout);
        ironLayout = view.findViewById(R.id.ironLayout);
        inductionLayout = view.findViewById(R.id.inductionLayout);
        routerLayout = view.findViewById(R.id.routerLayout);
        heaterLayout = view.findViewById(R.id.heaterLayout);
        D2HLayout = view.findViewById(R.id.D2HLayout);
        otherLayout = view.findViewById(R.id.otherLayout);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add " + applianceName + " details");

        builder.setView(view);

        switch (applianceName) {
            case "AC":

                if(ACLayout.getParent().getParent()!=null) {
                    ((ViewGroup) ACLayout.getParent().getParent()).removeView((ViewGroup)ACLayout.getParent());
                }
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

                ACRoomNo = view.findViewById(R.id.ACRoomNumberEditText);
                ACCompanyName = view.findViewById(R.id.ACCompanyNameEditText);
                ACModel = view.findViewById(R.id.ACModelEditText);
                ACCapacity = view.findViewById(R.id.ACCapacityEditText);
                ACLastServiceDate = view.findViewById(R.id.ACLastServiceDateEditText);
                ACStarRating = view.findViewById(R.id.ACStarRatingEditText);
                ACType = view.findViewById(R.id.ACTypeEditText);

                break;
            case "Fan":

                if(fanLayout.getParent().getParent()!=null) {
                    ((ViewGroup) fanLayout.getParent().getParent()).removeView((ViewGroup)fanLayout.getParent());
                }
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

                FanRoomNo = view.findViewById(R.id.fanRoomNumberEditText);
                FanCompanyName = view.findViewById(R.id.fanCompanyNameEditText);
                FanModel = view.findViewById(R.id.fanModelEditText);
                FanDays = view.findViewById(R.id.fanDaysEditText);
                FanBlades = view.findViewById(R.id.fanBladesEditText);


                break;
            case "Lift":

                if(liftLayout.getParent().getParent()!=null) {
                    ((ViewGroup) liftLayout.getParent().getParent()).removeView((ViewGroup)liftLayout.getParent());
                }
                ACLayout.setVisibility(View.GONE);
                fanLayout.setVisibility(View.GONE);
                liftLayout.setVisibility(View.VISIBLE);
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

                LiftCompanyName = view.findViewById(R.id.liftCompanyNameEditText);
                LiftModel = view.findViewById(R.id.liftModelEditText);
                LiftDays = view.findViewById(R.id.liftDaysEditText);
                LiftCapacity = view.findViewById(R.id.liftCapacityEditText);
                LiftDoor = view.findViewById(R.id.liftDoorEditText);

                break;
            case "Geyser":

                if(geyserLayout.getParent().getParent()!=null) {
                    ((ViewGroup) geyserLayout.getParent().getParent()).removeView((ViewGroup)geyserLayout.getParent());
                }
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

                GeyserRoomNo = view.findViewById(R.id.geyserRoomNumberEditText);
                GeyserCompanyName = view.findViewById(R.id.geyserCompanyNameEditText);
                GeyserModel = view.findViewById(R.id.geyserModelEditText);
                GeyserDays = view.findViewById(R.id.geyserDaysEditText);
                GeyserCapacity = view.findViewById(R.id.geyserCapacityEditText);
                GeyserPower = view.findViewById(R.id.geyserPowerEditText);
                GeyserRating = view.findViewById(R.id.geyserRatingEditText);

                break;
            case "Washing Machine":

                if(washingMachineLayout.getParent().getParent()!=null) {
                    ((ViewGroup) washingMachineLayout.getParent().getParent()).removeView((ViewGroup)washingMachineLayout.getParent());
                }
                ACLayout.setVisibility(View.GONE);
                fanLayout.setVisibility(View.GONE);
                liftLayout.setVisibility(View.GONE);
                geyserLayout.setVisibility(View.GONE);
                washingMachineLayout.setVisibility(View.VISIBLE);
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

                WashingMachineRoomNo = view.findViewById(R.id.wmRoomNumberEditText);
                WashingMachineCompanyName = view.findViewById(R.id.wmCompanyNameEditText);
                WashingMachineModel = view.findViewById(R.id.wmModelEditText);
                WashingMachineDays = view.findViewById(R.id.wmDaysEditText);
                WashingMachineCapacity = view.findViewById(R.id.wmCapacityEditText);
                WashingMachinePower = view.findViewById(R.id.wmPowerEditText);
                WashingMachineRating = view.findViewById(R.id.wmRatingEditText);
                WashingMachineType = view.findViewById(R.id.wmTypeEditText);

                break;
            case "RO":

                if(ROLayout.getParent().getParent()!=null) {
                    ((ViewGroup) ROLayout.getParent().getParent()).removeView((ViewGroup)ROLayout.getParent());
                }
                ACLayout.setVisibility(View.GONE);
                fanLayout.setVisibility(View.GONE);
                liftLayout.setVisibility(View.GONE);
                geyserLayout.setVisibility(View.GONE);
                washingMachineLayout.setVisibility(View.GONE);
                ROLayout.setVisibility(View.VISIBLE);
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

                ROCapacity = view.findViewById(R.id.ROCapacityEditText);
                ROCompanyName = view.findViewById(R.id.ROCompanyNameEditText);
                RODays = view.findViewById(R.id.RODaysEditText);
                ROModel = view.findViewById(R.id.ROModelEditText);
                RORoomNo = view.findViewById(R.id.RORoomNumberEditText);

                break;
            case "Dishwasher":
                if(dishwasherLayout.getParent().getParent()!=null) {
                    ((ViewGroup) dishwasherLayout.getParent().getParent()).removeView((ViewGroup)dishwasherLayout.getParent());
                }
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

                DishwasherCapacity = view.findViewById(R.id.dishwasherCapacityEditText);
                DishwasherCompanyName = view.findViewById(R.id.dishwasherCompanyNameEditText);
                DishwasherDays = view.findViewById(R.id.dishwasherDaysEditText);
                DishwasherModel = view.findViewById(R.id.dishwasherModelEditText);
                DishwasherRoomNo = view.findViewById(R.id.dishwasherRoomNumberEditText);
                DishwasherType = view.findViewById(R.id.dishwasherTypeEditText);

                break;
            case "Microwave":
                if(microwaveLayout.getParent().getParent()!=null) {
                    ((ViewGroup) ACLayout.getParent().getParent()).removeView((ViewGroup)microwaveLayout.getParent());
                }
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

                MicrowaveCapacity = view.findViewById(R.id.microwaveCapacityEditText);
                MicrowaveCompanyName = view.findViewById(R.id.microwaveCompanyNameEditText);
                MicrowaveDays = view.findViewById(R.id.microwaveDaysEditText);
                MicrowaveModel = view.findViewById(R.id.microwaveModelEditText);
                MicrowaveType = view.findViewById(R.id.microwaveTypeEditText);
                MicrowaveRoomNo = view.findViewById(R.id.microwaveRoomNumberEditText);

                break;
            case "Refrigerator":
                if(fridgeLayout.getParent().getParent()!=null) {
                    ((ViewGroup) fridgeLayout.getParent().getParent()).removeView((ViewGroup)fridgeLayout.getParent());
                }
                ACLayout.setVisibility(View.GONE);
                fanLayout.setVisibility(View.GONE);
                liftLayout.setVisibility(View.GONE);
                geyserLayout.setVisibility(View.GONE);
                washingMachineLayout.setVisibility(View.GONE);
                ROLayout.setVisibility(View.GONE);
                dishwasherLayout.setVisibility(View.GONE);
                microwaveLayout.setVisibility(View.GONE);
                fridgeLayout.setVisibility(View.VISIBLE);
                TVLayout.setVisibility(View.GONE);
                CCTVLayout.setVisibility(View.GONE);
                ironLayout.setVisibility(View.GONE);
                inductionLayout.setVisibility(View.GONE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                FridgeCapacity = view.findViewById(R.id.fridgeCapacityEditText);
                FridgeCompanyName = view.findViewById(R.id.fridgeCompanyNameEditText);
                FridgeDays = view.findViewById(R.id.fridgeDaysEditText);
                FridgeModel = view.findViewById(R.id.fridgeModelEditText);
                FridgeRoomNo = view.findViewById(R.id.fridgeRoomNumberEditText);
                FridgeType = view.findViewById(R.id.fridgeTypeEditText);
                FridgeRating = view.findViewById(R.id.fridgeRatingEditText);

                break;
            case "TV":
                if(TVLayout.getParent().getParent()!=null) {
                    ((ViewGroup) TVLayout.getParent().getParent()).removeView((ViewGroup)TVLayout.getParent());
                }
                ACLayout.setVisibility(View.GONE);
                fanLayout.setVisibility(View.GONE);
                liftLayout.setVisibility(View.GONE);
                geyserLayout.setVisibility(View.GONE);
                washingMachineLayout.setVisibility(View.GONE);
                ROLayout.setVisibility(View.GONE);
                dishwasherLayout.setVisibility(View.GONE);
                microwaveLayout.setVisibility(View.GONE);
                fridgeLayout.setVisibility(View.GONE);
                TVLayout.setVisibility(View.VISIBLE);
                CCTVLayout.setVisibility(View.GONE);
                ironLayout.setVisibility(View.GONE);
                inductionLayout.setVisibility(View.GONE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                TVCompanyName = view.findViewById(R.id.TVCompanyNameEditText);
                TVDays = view.findViewById(R.id.TVDaysEditText);
                TVModel = view.findViewById(R.id.TVModelEditText);
                TVResolution = view.findViewById(R.id.TVResolutionEditText);
                TVRoomNo = view.findViewById(R.id.TVRoomNumberEditText);
                TVSize = view.findViewById(R.id.TVSizeEditText);
                TVType = view.findViewById(R.id.TVTypeEditText);

                break;
            case "CCTV":
                if(CCTVLayout.getParent().getParent()!=null) {
                    ((ViewGroup) CCTVLayout.getParent().getParent()).removeView((ViewGroup)CCTVLayout.getParent());
                }
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
                CCTVLayout.setVisibility(View.VISIBLE);
                ironLayout.setVisibility(View.GONE);
                inductionLayout.setVisibility(View.GONE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                CCTVChanel = view.findViewById(R.id.CCTVChannelEditText);
                CCTVCompanyName = view.findViewById(R.id.CCTVCompanyNameEditText);
                CCTVDays = view.findViewById(R.id.CCTVDaysEditText);
                CCTVModel = view.findViewById(R.id.CCTVModelEditText);
                CCTVNight = view.findViewById(R.id.CCTVNightEditText);
                CCTVResolution = view.findViewById(R.id.CCTVResolutionEditText);
                CCTVRoomNo = view.findViewById(R.id.CCTVRoomNumberEditText);

                break;
            case "Iron":
                if(ironLayout.getParent().getParent()!=null) {
                    ((ViewGroup) ironLayout.getParent().getParent()).removeView((ViewGroup)ironLayout.getParent());
                }
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

                break;
            case "Induction":

                if(inductionLayout.getParent().getParent()!=null) {
                    ((ViewGroup) inductionLayout.getParent().getParent()).removeView((ViewGroup)inductionLayout.getParent());
                }
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
                inductionLayout.setVisibility(View.VISIBLE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                break;
            case "Router":

                if(routerLayout.getParent().getParent()!=null) {
                    ((ViewGroup) routerLayout.getParent().getParent()).removeView((ViewGroup)routerLayout.getParent());
                }
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

                break;
            case "Heater":

                if(heaterLayout.getParent().getParent()!=null) {
                    ((ViewGroup) heaterLayout.getParent().getParent()).removeView((ViewGroup)heaterLayout.getParent());
                }
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

                break;
            case "D2H":
                if(D2HLayout.getParent().getParent()!=null) {
                    ((ViewGroup) D2HLayout.getParent().getParent()).removeView((ViewGroup)D2HLayout.getParent());
                }
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
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.VISIBLE);
                otherLayout.setVisibility(View.GONE);

                break;
            case "Other":
                if(otherLayout.getParent().getParent()!=null) {
                    ((ViewGroup) otherLayout.getParent().getParent()).removeView((ViewGroup)otherLayout.getParent());
                }
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
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.VISIBLE);

                break;
        }

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (applianceName.equals("AC")) {


                }

            }
        });
        builder.setNegativeButton("Cancel", null);

        builder.show();

    }

}