package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.EazyPG.owner.Activities.RoomApplianceDetailsActivity;
import com.example.EazyPG.owner.Activities.RoomTenantDetailsActivity;
import com.example.EazyPG.owner.DetailsClasses.TenantDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private TextView first, second , third , fourth, tenant1NameTextView, tenant2NameTextView, tenant3NameTextView, name2TextView, name3TextView;
    private ListView dialogListView;
    private List<String> roomTypeList;
    private List<TenantDetails> tenantList = new ArrayList<>();

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private Button applianceButton, tenantButton;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;

    DatabaseReference databaseReference, databaseReference1;

    public RoomsDetailList(Activity context, List<String> roomList, List<String> roomTypeList) {
        super(context, R.layout.room_row, roomList);

        this.context = context;
        this.roomList = roomList;
        this.roomTypeList = roomTypeList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final LayoutInflater inflater = context.getLayoutInflater();
        final View listViewItemRoom = inflater.inflate(R.layout.room_row, null, true);

        first = listViewItemRoom.findViewById(R.id.firstTextView);
        second = listViewItemRoom.findViewById(R.id.secondTextView);
        third = listViewItemRoom.findViewById(R.id.thirdTextView);
        fourth = listViewItemRoom.findViewById(R.id.fourthTextView);
        tenant1NameTextView = listViewItemRoom.findViewById(R.id.tenant1NameTextView);
        tenant2NameTextView = listViewItemRoom.findViewById(R.id.tenant2NameTextView);
        tenant3NameTextView = listViewItemRoom.findViewById(R.id.tenant3NameTextView);
        name2TextView = listViewItemRoom.findViewById(R.id.name2TextView);
        name3TextView = listViewItemRoom.findViewById(R.id.name3TextView);

        applianceButton = listViewItemRoom.findViewById(R.id.appliancesButton);
        tenantButton = listViewItemRoom.findViewById(R.id.tenantButton);

        third.setText(roomList.get(position));
        fourth.setText(roomTypeList.get(position));

        applianceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, RoomApplianceDetailsActivity.class);
                String message = roomList.get(position);
                intent.putExtra(EXTRA_MESSAGE, message);
                context.startActivity(intent);
            }
        });

        tenantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        databaseReference = firebaseDatabase.getReference("PG/" + firebaseUser.getUid() + "/Tenants/CurrentTenants");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tenantList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    TenantDetails tenantDetails = snapshot.getValue(TenantDetails.class);
                    tenantList.add(tenantDetails);
                }

                databaseReference1 = firebaseDatabase.getReference("PG/" + firebaseUser.getUid());

                if(roomTypeList.get(position).equals("One Bed")){
                    tenant2NameTextView.setVisibility(View.GONE);
                    tenant3NameTextView.setVisibility(View.GONE);
                    name2TextView.setVisibility(View.GONE);
                    name3TextView.setVisibility(View.GONE);
                }

                if(roomTypeList.get(position).equals("Two Bed")){
                    tenant3NameTextView.setVisibility(View.GONE);
                    name3TextView.setVisibility(View.GONE);
                }

                for(int i = 0; i < tenantList.size(); i++){
                    if (tenantList.get(i).room.equals(roomList.get(position))) {
                        if(tenant1NameTextView.getText().toString().isEmpty()){
                            tenant1NameTextView.setText(tenantList.get(i).name);
                        }else if(!tenant1NameTextView.getText().toString().isEmpty() && tenant2NameTextView.getVisibility() == View.VISIBLE){
                            tenant2NameTextView.setText(tenantList.get(i).name);
                        }else if(!tenant1NameTextView.getText().toString().isEmpty() && !tenant2NameTextView.getText().toString().isEmpty() && tenant3NameTextView.getVisibility() == View.VISIBLE){
                            tenant3NameTextView.setText(tenantList.get(i).name);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewItemRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RoomTenantDetailsActivity.class);
                String message = roomList.get(position);
                intent.putExtra(EXTRA_MESSAGE, message);
                context.startActivity(intent);
            }
        });

        return listViewItemRoom;
    }
}
