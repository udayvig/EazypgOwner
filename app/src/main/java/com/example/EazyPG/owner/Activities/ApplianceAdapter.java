package com.example.EazyPG.owner.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EazyPG.owner.Appliances.ACDetails;
import com.example.EazyPG.owner.Appliances.CCTVDetails;
import com.example.EazyPG.owner.Appliances.D2HDetails;
import com.example.EazyPG.owner.Appliances.DishwasherDetails;
import com.example.EazyPG.owner.Appliances.FanDetails;
import com.example.EazyPG.owner.Appliances.GeyserDetails;
import com.example.EazyPG.owner.Appliances.HeaterDetails;
import com.example.EazyPG.owner.Appliances.InductionDetails;
import com.example.EazyPG.owner.Appliances.IronDetails;
import com.example.EazyPG.owner.Appliances.LiftDetails;
import com.example.EazyPG.owner.Appliances.MicrowaveDetails;
import com.example.EazyPG.owner.Appliances.OtherApplianceDetails;
import com.example.EazyPG.owner.Appliances.RODetails;
import com.example.EazyPG.owner.Appliances.RefrigeratorDetails;
import com.example.EazyPG.owner.Appliances.RouterDetails;
import com.example.EazyPG.owner.Appliances.TVDetails;
import com.example.EazyPG.owner.Appliances.WashingMachineDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
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

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

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
    private EditText InductionRoomNo, InductionCompanyName, InductionModel, InductionDays, InductionPower, InductionType, InductionNoCooktop;
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

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public String[] appliances={"AC","Fan","Lift","Geyser","Washing Machine",
                "RO","Dishwasher","Microwave","Refrigerator","TV","CCTV","Iron",
                "Induction","Router","Heater","D2H","Other"};
        private TextView iconTextView;
        private Button iconButton;
        private final Context context;
        private CardView cardViewItem;

        public MyViewHolder(final View itemView, ClickListener listener){
            super(itemView);

            iconTextView = itemView.findViewById(R.id.iconTextView);
            iconButton = itemView.findViewById(R.id.iconButton);
            cardViewItem = itemView.findViewById(R.id.cardView2);
            
            context = itemView.getContext();

        }


        public void bind(MyViewHolder holder, int index){
            iconTextView.setText(appliances[index]);

            holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent intent;
                        String message;

                        switch (getAdapterPosition()) {

                            case 0:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "AC";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 1:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Fan";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 2:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Lift";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 3:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Geyser";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 4:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Washing Machine";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 5:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "RO";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 6:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Dishwasher";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 7:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Microwave";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 8:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Refrigerator";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 9:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "TV";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 10:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "CCTV";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 11:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Iron";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 12:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Induction";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 13:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Router";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 14:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Heater";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 15:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "D2H";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                            case 16:
                                intent = new Intent(context, ApplianceDetailsActivity.class);
                                message = "Other";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                context.startActivity(intent);
                                break;

                        }

                }
            });

            holder.iconButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    databaseReference = firebaseDatabase.getReference(firebaseUser.getUid());

                    switch (iconTextView.getText().toString()) {
                        case "AC":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Fan":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Lift":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Geyser":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Washing Machine":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "RO":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Dishwasher":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Microwave":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Refrigerator":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "TV":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "CCTV":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Iron":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Induction":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Router":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Heater":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "D2H":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                        case "Other":
                            showDialog(iconTextView.getText().toString(), view.getContext());
                            break;
                    }
                }
            });
        }
    }


    private void showDialog(final String applianceName, final Context context) {

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

                final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Saving...", true);

                switch (applianceName) {
                    case "AC":

                        ACRoomNo = view.findViewById(R.id.ACRoomNumberEditText);
                        ACCompanyName = view.findViewById(R.id.ACCompanyNameEditText);
                        ACModel = view.findViewById(R.id.ACModelEditText);
                        ACCapacity = view.findViewById(R.id.ACCapacityEditText);
                        ACLastServiceDate = view.findViewById(R.id.ACLastServiceDateEditText);
                        ACStarRating = view.findViewById(R.id.ACStarRatingEditText);
                        ACType = view.findViewById(R.id.ACTypeEditText);

                        String roomNoAC = ACRoomNo.getText().toString();
                        String brandAC = ACCompanyName.getText().toString();
                        String modelAC = ACModel.getText().toString();
                        String capacityAC = ACCapacity.getText().toString();
                        String lastServiceDateAC = ACLastServiceDate.getText().toString();
                        String starRatingAC = ACStarRating.getText().toString();
                        String ratingAC = ACType.getText().toString();
                        String uidAC = databaseReference.push().getKey();

                        if (roomNoAC.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        } else {

                            ACDetails acDetails = new ACDetails(uidAC, roomNoAC, brandAC, modelAC, capacityAC, lastServiceDateAC, starRatingAC, ratingAC);
                            databaseReference.child("Appliances").child("AC").child(uidAC).setValue(acDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        break;
                    case "Fan":

                        FanRoomNo = view.findViewById(R.id.fanRoomNumberEditText);
                        FanCompanyName = view.findViewById(R.id.fanCompanyNameEditText);
                        FanModel = view.findViewById(R.id.fanModelEditText);
                        FanDays = view.findViewById(R.id.fanDaysEditText);
                        FanBlades = view.findViewById(R.id.fanBladesEditText);

                        String roomNoFan = FanRoomNo.getText().toString();
                        String brandFan = FanCompanyName.getText().toString();
                        String modelFan = FanModel.getText().toString();
                        String timeSinceInstallationFan = FanDays.getText().toString();
                        String noOfBladesFan = FanBlades.getText().toString();
                        String uidFan = databaseReference.push().getKey();

                        if (roomNoFan.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        } else {

                            FanDetails fanDetails = new FanDetails(uidFan, roomNoFan, brandFan, modelFan, timeSinceInstallationFan, noOfBladesFan);
                            databaseReference.child("Appliances").child("Fan").child(uidFan).setValue(fanDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        break;
                    case "Lift":

                        LiftCompanyName = view.findViewById(R.id.liftCompanyNameEditText);
                        LiftModel = view.findViewById(R.id.liftModelEditText);
                        LiftDays = view.findViewById(R.id.liftDaysEditText);
                        LiftCapacity = view.findViewById(R.id.liftCapacityEditText);
                        LiftDoor = view.findViewById(R.id.liftDoorEditText);

                        String brandLift = LiftCompanyName.getText().toString();
                        String modelLift = LiftModel.getText().toString();
                        String daysLift = LiftDays.getText().toString();
                        String capacityLift = LiftCapacity.getText().toString();
                        String doorLift = LiftDoor.getText().toString();
                        String uidLift = databaseReference.push().getKey();

                        LiftDetails liftDetails = new LiftDetails(uidLift, brandLift, modelLift, daysLift, capacityLift, doorLift);

                        databaseReference.child("Appliances").child("Lift").child(uidLift).setValue(liftDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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


                        break;
                    case "Geyser":

                        GeyserRoomNo = view.findViewById(R.id.geyserRoomNumberEditText);
                        GeyserCompanyName = view.findViewById(R.id.geyserCompanyNameEditText);
                        GeyserModel = view.findViewById(R.id.geyserModelEditText);
                        GeyserDays = view.findViewById(R.id.geyserDaysEditText);
                        GeyserCapacity = view.findViewById(R.id.geyserCapacityEditText);
                        GeyserPower = view.findViewById(R.id.geyserPowerEditText);
                        GeyserRating = view.findViewById(R.id.geyserRatingEditText);

                        String roomNoGeyser = GeyserRoomNo.getText().toString();
                        String brandGeyser = GeyserCompanyName.getText().toString();
                        String modelGeyser = GeyserModel.getText().toString();
                        String daysGeyser = GeyserDays.getText().toString();
                        String capacityGeyser = GeyserCapacity.getText().toString();
                        String powerGeyser = GeyserPower.getText().toString();
                        String ratingGeyser = GeyserRating.getText().toString();
                        String uidGeyser = databaseReference.push().getKey();

                        if (roomNoGeyser.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        } else {

                            GeyserDetails geyserDetails = new GeyserDetails(uidGeyser, roomNoGeyser, brandGeyser, modelGeyser, daysGeyser, capacityGeyser, powerGeyser, ratingGeyser);
                            databaseReference.child("Appliances").child("Geyser").child(uidGeyser).setValue(geyserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        break;

                    case "Washing Machine":

                        WashingMachineRoomNo = view.findViewById(R.id.wmRoomNumberEditText);
                        WashingMachineCompanyName = view.findViewById(R.id.wmCompanyNameEditText);
                        WashingMachineModel = view.findViewById(R.id.wmModelEditText);
                        WashingMachineDays = view.findViewById(R.id.wmDaysEditText);
                        WashingMachineCapacity = view.findViewById(R.id.wmCapacityEditText);
                        WashingMachinePower = view.findViewById(R.id.wmPowerEditText);
                        WashingMachineRating = view.findViewById(R.id.wmRatingEditText);
                        WashingMachineType = view.findViewById(R.id.wmTypeEditText);

                        String roomNoWashingMachine = WashingMachineRoomNo.getText().toString();
                        String brandWashingMachine = WashingMachineCompanyName.getText().toString();
                        String modelWashingMachine = WashingMachineModel.getText().toString();
                        String daysWashingMachine = WashingMachineDays.getText().toString();
                        String capacityWashingMachine = WashingMachineCapacity.getText().toString();
                        String powerWashingMachine = WashingMachinePower.getText().toString();
                        String ratingWashingMachine = WashingMachineRating.getText().toString();
                        String typeWashingMachine = WashingMachineType.getText().toString();
                        String uidWashingMachine = databaseReference.push().getKey();

                        if (brandWashingMachine.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        } else {

                            WashingMachineDetails washingMachineDetails = new WashingMachineDetails(uidWashingMachine, roomNoWashingMachine, brandWashingMachine, modelWashingMachine, daysWashingMachine, capacityWashingMachine, powerWashingMachine, ratingWashingMachine, typeWashingMachine);
                            databaseReference.child("Appliances").child("Washing Machine").child(uidWashingMachine).setValue(washingMachineDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        break;
                    case "RO":

                        ROCapacity = view.findViewById(R.id.ROCapacityEditText);
                        ROCompanyName = view.findViewById(R.id.ROCompanyNameEditText);
                        RODays = view.findViewById(R.id.RODaysEditText);
                        ROModel = view.findViewById(R.id.ROModelEditText);
                        RORoomNo = view.findViewById(R.id.RORoomNumberEditText);

                        String capacityRO = ROCapacity.getText().toString();
                        String brandRO = ROCompanyName.getText().toString();
                        String daysRO = RODays.getText().toString();
                        String modelRO = ROModel.getText().toString();
                        String roomNoRO = RORoomNo.getText().toString();
                        String uidRO = databaseReference.push().getKey();

                        if (brandRO.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }

                        else {
                            RODetails roDetails = new RODetails(uidRO, roomNoRO, brandRO, modelRO, daysRO, capacityRO);
                            databaseReference.child("Appliances").child("RO").child(uidRO).setValue(roDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        break;
                    case "Dishwasher":

                        DishwasherCapacity = view.findViewById(R.id.dishwasherCapacityEditText);
                        DishwasherCompanyName = view.findViewById(R.id.dishwasherCompanyNameEditText);
                        DishwasherDays = view.findViewById(R.id.dishwasherDaysEditText);
                        DishwasherModel = view.findViewById(R.id.dishwasherModelEditText);
                        DishwasherRoomNo = view.findViewById(R.id.dishwasherRoomNumberEditText);
                        DishwasherType = view.findViewById(R.id.dishwasherTypeEditText);

                        String capacityDishwasher = DishwasherCapacity.getText().toString();
                        String brandDishwasher = DishwasherCompanyName.getText().toString();
                        String daysDishwasher = DishwasherDays.getText().toString();
                        String modelDishwasher = DishwasherModel.getText().toString();
                        String roomNoDishwasher = DishwasherRoomNo.getText().toString();
                        String typeDishwasher = DishwasherType.getText().toString();
                        String uidDishwasher = databaseReference.push().getKey();

                        if (brandDishwasher.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                        else {
                            DishwasherDetails dishwasherDetails = new DishwasherDetails(uidDishwasher, roomNoDishwasher, brandDishwasher, modelDishwasher, daysDishwasher, capacityDishwasher, typeDishwasher);
                            databaseReference.child("Appliances").child("Dishwasher").child(uidDishwasher).setValue(dishwasherDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                        break;
                    case "Microwave":

                        MicrowaveCapacity = view.findViewById(R.id.microwaveCapacityEditText);
                        MicrowaveCompanyName = view.findViewById(R.id.microwaveCompanyNameEditText);
                        MicrowaveDays = view.findViewById(R.id.microwaveDaysEditText);
                        MicrowaveModel = view.findViewById(R.id.microwaveModelEditText);
                        MicrowaveType = view.findViewById(R.id.microwaveTypeEditText);
                        MicrowaveRoomNo = view.findViewById(R.id.microwaveRoomNumberEditText);

                        String capacityMicrowave = MicrowaveCapacity.getText().toString();
                        String brandMicrowave = MicrowaveCompanyName.getText().toString();
                        String daysMicrowave = MicrowaveDays.getText().toString();
                        String modelMicrowave = MicrowaveModel.getText().toString();
                        String typeMicrowave = MicrowaveType.getText().toString();
                        String roomNoMicrowave = MicrowaveRoomNo.getText().toString();
                        String uidMicrowave = databaseReference.push().getKey();

                        if (brandMicrowave.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }

                        else {
                            MicrowaveDetails microwaveDetails = new MicrowaveDetails(uidMicrowave, roomNoMicrowave, brandMicrowave, modelMicrowave, daysMicrowave, capacityMicrowave, typeMicrowave);
                            databaseReference.child("Appliances").child("Microwave").child(uidMicrowave).setValue(microwaveDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        break;
                    case "Refrigerator":

                        FridgeCapacity = view.findViewById(R.id.fridgeCapacityEditText);
                        FridgeCompanyName = view.findViewById(R.id.fridgeCompanyNameEditText);
                        FridgeDays = view.findViewById(R.id.fridgeDaysEditText);
                        FridgeModel = view.findViewById(R.id.fridgeModelEditText);
                        FridgeRoomNo = view.findViewById(R.id.fridgeRoomNumberEditText);
                        FridgeType = view.findViewById(R.id.fridgeTypeEditText);
                        FridgeRating = view.findViewById(R.id.fridgeRatingEditText);

                        String capacityFridge = FridgeCapacity.getText().toString();
                        String brandFridge = FridgeCompanyName.getText().toString();
                        String daysFridge = FridgeDays.getText().toString();
                        String modelFridge = FridgeModel.getText().toString();
                        String roomNoFridge = FridgeRoomNo.getText().toString();
                        String typeFridge = FridgeType.getText().toString();
                        String ratingFridge = FridgeRating.getText().toString();
                        String uidFridge = databaseReference.push().getKey();

                        if (brandFridge.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else {

                            RefrigeratorDetails refrigeratorDetails = new RefrigeratorDetails(uidFridge, roomNoFridge, brandFridge, modelFridge, daysFridge, capacityFridge, typeFridge, ratingFridge);
                            databaseReference.child("Appliances").child("Refrigerator").child(uidFridge).setValue(refrigeratorDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        break;
                    case "TV":

                        TVCompanyName = view.findViewById(R.id.TVCompanyNameEditText);
                        TVDays = view.findViewById(R.id.TVDaysEditText);
                        TVModel = view.findViewById(R.id.TVModelEditText);
                        TVResolution = view.findViewById(R.id.TVResolutionEditText);
                        TVRoomNo = view.findViewById(R.id.TVRoomNumberEditText);
                        TVSize = view.findViewById(R.id.TVSizeEditText);
                        TVType = view.findViewById(R.id.TVTypeEditText);

                        String brandTV = TVCompanyName.getText().toString();
                        String daysTV = TVDays.getText().toString();
                        String modelTV = TVModel.getText().toString();
                        String resolutionTV = TVResolution.getText().toString();
                        String roomNoTV = TVRoomNo.getText().toString();
                        String sizeTV = TVSize.getText().toString();
                        String typeTV = TVType.getText().toString();
                        String uidTV = databaseReference.push().getKey();

                        if (brandTV.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else {

                            TVDetails tvDetails = new TVDetails(uidTV, roomNoTV, brandTV, modelTV, daysTV, typeTV, sizeTV, resolutionTV);
                            databaseReference.child("Appliances").child("TV").child(uidTV).setValue(tvDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        break;
                    case "CCTV":

                        CCTVChanel = view.findViewById(R.id.CCTVChannelEditText);
                        CCTVCompanyName = view.findViewById(R.id.CCTVCompanyNameEditText);
                        CCTVDays = view.findViewById(R.id.CCTVDaysEditText);
                        CCTVModel = view.findViewById(R.id.CCTVModelEditText);
                        CCTVNight = view.findViewById(R.id.CCTVNightEditText);
                        CCTVResolution = view.findViewById(R.id.CCTVResolutionEditText);
                        CCTVRoomNo = view.findViewById(R.id.CCTVRoomNumberEditText);

                        String channelCCTV = CCTVChanel.getText().toString();
                        String brandCCTV = CCTVCompanyName.getText().toString();
                        String daysCCTV = CCTVDays.getText().toString();
                        String modelCCTV = CCTVModel.getText().toString();
                        String nightCCTV = CCTVNight.getText().toString();
                        String resolutionCCTV = CCTVResolution.getText().toString();
                        String roomNoCCTV = CCTVRoomNo.getText().toString();
                        String uidCCTV = databaseReference.push().getKey();

                        if (roomNoCCTV.equals("") && brandCCTV.equals("") && daysCCTV.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                        else {

                            CCTVDetails cctvDetails = new CCTVDetails(uidCCTV, roomNoCCTV, brandCCTV, modelCCTV, daysCCTV, nightCCTV, channelCCTV, resolutionCCTV);
                            databaseReference.child("Appliances").child("CCTV").child(uidCCTV).setValue(cctvDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        break;
                    case "Iron":

                        IronComanyName = view.findViewById(R.id.ironCompanyNameEditText);
                        IronDays = view.findViewById(R.id.ironDaysEditText);
                        IronModel = view.findViewById(R.id.ironModelEditText);
                        IronPower = view.findViewById(R.id.ironPowerEditText);
                        IronRoomNo = view.findViewById(R.id.ironRoomNumberEditText);

                        String brandIron = IronComanyName.getText().toString();
                        String daysIron = IronDays.getText().toString();
                        String modelIron = IronModel.getText().toString();
                        String powerIron = IronPower.getText().toString();
                        String roomNoIron = IronRoomNo.getText().toString();
                        String uidIron = databaseReference.push().getKey();

                        if (brandIron.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else {
                            IronDetails ironDetails = new IronDetails(uidIron, roomNoIron, brandIron, modelIron, daysIron, powerIron);
                            databaseReference.child("Appliances").child("Iron").child(uidIron).setValue(ironDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        break;
                    case "Induction":

                        InductionCompanyName = view.findViewById(R.id.inductionCompanyNameEditText);
                        InductionDays = view.findViewById(R.id.inductionDaysEditText);
                        InductionModel = view.findViewById(R.id.inductionModelEditText);
                        InductionNoCooktop = view.findViewById(R.id.inductionNumberCooktopEditText);
                        InductionPower = view.findViewById(R.id.inductionPowerEditText);
                        InductionRoomNo = view.findViewById(R.id.inductionRoomNumberEditText);
                        InductionType = view.findViewById(R.id.inductionControlTypeEditText);

                        String brandInduction = InductionCompanyName.getText().toString();
                        String daysInduction = InductionDays.getText().toString();
                        String modelInduction = InductionModel.getText().toString();
                        String noCooktopInduction = InductionNoCooktop.getText().toString();
                        String powerInduction = InductionPower.getText().toString();
                        String roomNoInduction = InductionRoomNo.getText().toString();
                        String typeInduction = InductionType.getText().toString();
                        String uidInduction = databaseReference.push().getKey();

                        if (brandInduction.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else {

                            InductionDetails inductionDetails = new InductionDetails(uidInduction, roomNoInduction, brandInduction, modelInduction, daysInduction, powerInduction, typeInduction, noCooktopInduction);
                            databaseReference.child("Appliances").child("Induction").child(uidInduction).setValue(inductionDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        break;
                    case "Router":

                        RouterAntenna = view.findViewById(R.id.routerAntennaEditText);
                        RouterCompanyName = view.findViewById(R.id.routerCompanyNameEditText);
                        RouterDays = view.findViewById(R.id.routerDaysEditText);
                        RouterModel = view.findViewById(R.id.routerModelEditText);
                        RouterRoomNo = view.findViewById(R.id.routerRoomNumberEditText);
                        RouterSpeed = view.findViewById(R.id.routerSpeedEditText);

                        String antennaRouter = RouterAntenna.getText().toString();
                        String brandRouter = RouterCompanyName.getText().toString();
                        String daysRouter = RouterDays.getText().toString();
                        String modelRouter = RouterModel.getText().toString();
                        String roomNoRouter = RouterRoomNo.getText().toString();
                        String speedRouter = RouterSpeed.getText().toString();
                        String uidRouter = databaseReference.push().getKey();

                        if (daysRouter.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else {

                            RouterDetails routerDetails = new RouterDetails(uidRouter, roomNoRouter, brandRouter, modelRouter, daysRouter, antennaRouter, speedRouter);
                            databaseReference.child("Appliances").child("Router").child(uidRouter).setValue(routerDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                        break;
                    case "Heater":

                        HeaterCompanyName = view.findViewById(R.id.heaterCompanyNameEditText);
                        HeaterDays = view.findViewById(R.id.heaterDaysEditText);
                        HeaterModel = view.findViewById(R.id.heaterModelEditText);
                        HeaterPower = view.findViewById(R.id.heaterPowerEditText);
                        HeaterRoomNo = view.findViewById(R.id.heaterRoomNumberEditText);
                        HeaterWeight = view.findViewById(R.id.heaterWeightEditText);

                        String brandHeater = HeaterCompanyName.getText().toString();
                        String daysHeater = HeaterDays.getText().toString();
                        String modelHeater = HeaterModel.getText().toString();
                        String powerHeater = HeaterPower.getText().toString();
                        String roomNoHeater = HeaterRoomNo.getText().toString();
                        String weightHeater = HeaterWeight.getText().toString();
                        String uidHeater = databaseReference.push().getKey();

                        if (roomNoHeater.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }

                        else {

                            HeaterDetails heaterDetails = new HeaterDetails(uidHeater, roomNoHeater, brandHeater, modelHeater, daysHeater, powerHeater, weightHeater);
                            databaseReference.child("Appliances").child("Heater").child(uidHeater).setValue(heaterDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                        break;
                    case "D2H":

                        D2HCompanyName = view.findViewById(R.id.D2HCompanyNameEditText);
                        D2HDays = view.findViewById(R.id.D2HDaysEditText);
                        D2HRoomNo = view.findViewById(R.id.D2HRoomNumberEditText);

                        String brandD2H = D2HCompanyName.getText().toString();
                        String daysD2H = D2HDays.getText().toString();
                        String roomNoD2H = D2HRoomNo.getText().toString();
                        String uidD2H = databaseReference.push().getKey();

                        if (brandD2H.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        else {

                            D2HDetails d2HDetails = new D2HDetails(uidD2H, roomNoD2H, brandD2H, daysD2H);
                            databaseReference.child("Appliances").child("D2H").child(uidD2H).setValue(d2HDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                        break;
                    case "Other":

                        OtherCompanyName = view.findViewById(R.id.otherCompanyNameEditText);
                        OtherName = view.findViewById(R.id.otherNameEditText);
                        OtherRoomNo = view.findViewById(R.id.otherRoomNumberEditText);

                        String brandOther = OtherCompanyName.getText().toString();
                        String nameOther = OtherName.getText().toString();
                        String roomNoOther = OtherRoomNo.getText().toString();
                        String uidOther = databaseReference.push().getKey();

                        if (brandOther.equals("") && nameOther.equals("")) {

                            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        else {
                            OtherApplianceDetails otherApplianceDetails = new OtherApplianceDetails(uidOther, roomNoOther, nameOther, brandOther);
                            databaseReference.child("Appliances").child("Other").child(uidOther).setValue(otherApplianceDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                        break;
                }

            }
        });
        builder.setNegativeButton("Cancel", null);

        builder.show();

    }

}