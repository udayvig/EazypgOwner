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

import com.example.EazyPG.owner.DetailsClasses.TenantDetails;
import com.example.ainesh.eazypg_owner.R;
import com.katepratik.msg91api.MSG91;

import java.util.List;

import static com.example.EazyPG.owner.Activities.RentCollectionPaidDetailList.EXTRA_MESSAGE;
import static com.example.EazyPG.owner.Activities.RentCollectionPaidDetailList.EXTRA_MESSAGE2;

public class RentCollectionUnpaidDetailList extends RecyclerView.Adapter<RentCollectionUnpaidDetailList.MyHolder>{

    List<TenantDetails> tenantDetails;
    List<TenantDetails> tenantUnpaidDetails;
    Context context;

    public RentCollectionUnpaidDetailList(List<TenantDetails> tenantDetails, List<TenantDetails> tenantUnpaidDetails, Context context) {
        this.tenantDetails = tenantDetails;
        this.tenantUnpaidDetails = tenantUnpaidDetails;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return tenantUnpaidDetails.size();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rent_bill_collection_rent_row, parent, false);

        return new RentCollectionUnpaidDetailList.MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.rentAmountTextView.setText(tenantUnpaidDetails.get(position).rentAmount);
        holder.tenantRoomTextView.setText(tenantUnpaidDetails.get(position).room);
        holder.tenantNameTextView.setText(tenantUnpaidDetails.get(position).name);

        holder.phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + tenantUnpaidDetails.get(position).phone));
                context.startActivity(callIntent);
            }
        });

        holder.messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MSG91 msg91 = new MSG91("163776AiifTBEVMZl5aae0bce");
                msg91.composeMessage("EazyPG", "Hi " + tenantUnpaidDetails.get(position).name + ". Your rent is due for this month.");
                msg91.to(tenantUnpaidDetails.get(position).phone);
                String sendStatus = msg91.send();
            }
        });

        holder.addFineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FineRentBillActivity.class);
                intent.putExtra(EXTRA_MESSAGE, tenantUnpaidDetails.get(position).id);
                intent.putExtra(EXTRA_MESSAGE2, tenantUnpaidDetails.get(position).room);
                context.startActivity(intent);
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
