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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Uday Vig on 09-Jul-18.
 */

public class ApplianceAdapter extends RecyclerView.Adapter<ApplianceAdapter.MyViewHolder>{

    private RelativeLayout ACLayout, fanLayout, liftLayout, geyserLayout, washingMachineLayout, ROLayout, dishwasherLayout, microwaveLayout,
            fridgeLayout, TVLayout, CCTVLayout, ironLayout, inductionLayout, routerLayout, heaterLayout, D2HLayout, otherLayout;

    //Interface
    public interface ClickListener {
        void onPositionClicked(int position);
    }

    LayoutInflater inflater;
    private final ClickListener clickListener;
    public String[] appliances;

    public ApplianceAdapter(String[] app, LayoutInflater inflater, ClickListener listener){
        this.clickListener=listener;
        this.appliances=app;
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


    private void showDialog(String applianceName, Context context) {

        View view = inflater.inflate(R.layout.dialog_appliance, null);

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

            }
        });
        builder.setNegativeButton("Cancel", null);

        builder.show();

    }

}