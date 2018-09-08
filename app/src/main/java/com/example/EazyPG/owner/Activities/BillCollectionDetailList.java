package com.example.EazyPG.owner.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.EazyPG.owner.DetailsClasses.BillDetails;
import com.example.EazyPG.owner.DetailsClasses.TenantDetails;
import com.example.ainesh.eazypg_owner.R;
import com.katepratik.msg91api.MSG91;

import java.util.List;

public class BillCollectionDetailList extends RecyclerView.Adapter<BillCollectionDetailList.MyHolder>{

    List<BillDetails> electricityBillDetails;
    List<BillDetails> wifiBillDetails;
    List<BillDetails> gasBillDetails;
    List<BillDetails> otherBillDetails;
    List<TenantDetails> tenantDetailList;
    Context context;

    public BillCollectionDetailList(List<BillDetails> electricityBillDetails, List<BillDetails> wifiBillDetails, List<BillDetails> gasBillDetails, List<BillDetails> otherBillDetails, List<TenantDetails> tenantDetailList, Context context) {
        this.electricityBillDetails = electricityBillDetails;
        this.wifiBillDetails = wifiBillDetails;
        this.gasBillDetails = gasBillDetails;
        this.otherBillDetails = otherBillDetails;
        this.tenantDetailList = tenantDetailList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return electricityBillDetails.size() + wifiBillDetails.size() + gasBillDetails.size() + otherBillDetails.size();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rent_bill_collection_rent_row, parent, false);

        return new BillCollectionDetailList.MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.rentAmountTextView.setText(tenantDetailList.get(position).rentAmount);
        holder.tenantRoomTextView.setText(tenantDetailList.get(position).room);
        holder.tenantNameTextView.setText(tenantDetailList.get(position).name);

        holder.phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + tenantDetailList.get(position).phone));
                context.startActivity(callIntent);
            }
        });

        holder.messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MSG91 msg91 = new MSG91("163776AiifTBEVMZl5aae0bce");
                msg91.composeMessage("EazyPG", "Hi " + tenantDetailList.get(position).name + ". Your rent is due for this month.");
                msg91.to(tenantDetailList.get(position).phone);
                String sendStatus = msg91.send();
            }
        });


    }

    public class MyHolder extends RecyclerView.ViewHolder{
        public TextView tenantNameTextView, tenantRoomTextView, rentAmountTextView;
        public Button messageButton, phoneButton, addFineButton;

        public MyHolder(View itemView){
            super(itemView);

            tenantNameTextView = itemView.findViewById(R.id.tenantNameTextView);
            tenantRoomTextView = itemView.findViewById(R.id.roomNumberTextView);
            rentAmountTextView = itemView.findViewById(R.id.rentAmountTextView);

            messageButton = itemView.findViewById(R.id.messageButton);
            phoneButton = itemView.findViewById(R.id.callButton);
            addFineButton = itemView.findViewById(R.id.fineButton);
        }
    }
}
