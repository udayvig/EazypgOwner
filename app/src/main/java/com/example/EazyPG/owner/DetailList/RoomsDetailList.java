package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RoomsDetailList extends ArrayAdapter<String> {

    private Activity context;
    private List<String> roomList;
    private TextView first, second , third , fourth;
    private List<String> roomTypeList;



    private Button applianceButton, tenantButton;

    public RoomsDetailList(Activity context, List<String> roomList, List<String> roomTypeList) {
        super(context, R.layout.room_row, roomList);

        this.context = context;
        this.roomList = roomList;
        this.roomTypeList = roomTypeList;

    }

    DatabaseReference databaseReference;


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemRoom = inflater.inflate(R.layout.room_row, null, true);
        first = listViewItemRoom.findViewById(R.id.firstTextView);
        second = listViewItemRoom.findViewById(R.id.secondTextView);
        third = listViewItemRoom.findViewById(R.id.thirdTextView);
        fourth = listViewItemRoom.findViewById(R.id.fourthTextView);

        applianceButton = listViewItemRoom.findViewById(R.id.appliancesButton);
        tenantButton = listViewItemRoom.findViewById(R.id.tenantButton);

        third.setText(roomList.get(position));
        fourth.setText(roomTypeList.get(position));

        applianceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchAppliance(roomList.get(position));



            }
        });

        tenantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return listViewItemRoom;
    }

    private void fetchAppliance(String room) {

        databaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + room + "/Appliance/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
