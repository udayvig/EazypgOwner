package com.example.EazyPG.owner.DetailList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.EazyPG.owner.DetailsClasses.BillDetails;
import com.example.EazyPG.owner.DetailsClasses.TenantDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddBillRoomGasDetailList extends RecyclerView.Adapter<AddBillRoomGasDetailList.MyHolder>{

    List<String> roomsList;
    List<String> roomsTypeList;
    Context context;

    public AddBillRoomGasDetailList(Context context, List<String> roomsList, List<String> roomsTypeList) {
        this.roomsList = roomsList;
        this.roomsTypeList = roomsTypeList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return roomsList.size();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_wifi_gas_bill_room_row, parent, false);

        return new AddBillRoomGasDetailList.MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        final String roomNumber = roomsList.get(position);
        final String roomType = roomsTypeList.get(position);

        holder.roomNumberTextView.setText(roomNumber);
        holder.roomTypeTextView.setText(roomType);

        //final List<TenantDetails> tenantList = new ArrayList<>();

        holder.saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                final DatabaseReference databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Rooms/" + roomNumber + "/Tenant/CurrentTenants/");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //tenantList.clear();

                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            TenantDetails tenantDetails = snapshot.getValue(TenantDetails.class);

                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                                Date date = new Date();
                                String dateStr = dateFormat.format(date);

                                String dateString = dateStr.substring(6,10) + "-" + dateStr.substring(3,5);

                                final String billAmount = holder.amountEditText.getText().toString();

                                String billId = databaseReference.push().getKey();
                                BillDetails billDetails = new BillDetails(billId, "Gas", billAmount, false, "", dateString);
                                /*DatabaseReference databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Rooms/" + roomNumber + "/Tenant/CurrentTenants/" + tenantDetails.id);
                                databaseReference1.child("Accounts").child("Bills").child(dateString).child(billId).setValue(billDetails);*/

                                DatabaseReference databaseReference2 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Tenants/CurrentTenants/" + tenantDetails.id);
                                databaseReference2.child("Accounts").child("Bills").child(dateString).child(billId).setValue(billDetails);

                                DatabaseReference databaseReference3 = firebaseDatabase.getReference("Tenants/" + tenantDetails.id);
                                databaseReference3.child("Accounts").child("Bills").child(dateString).child(billId).setValue(billDetails);

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        public TextView roomNumberTextView, roomTypeTextView;
        public EditText amountEditText;
        public FloatingActionButton saveFab;

        public MyHolder(View itemView){
            super(itemView);

            roomNumberTextView = itemView.findViewById(R.id.thirdTextView);
            roomTypeTextView = itemView.findViewById(R.id.fourthTextView);

            amountEditText = itemView.findViewById(R.id.billAmountEditText);

            saveFab = itemView.findViewById(R.id.saveFab);
        }
    }
}
