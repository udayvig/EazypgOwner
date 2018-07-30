package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RoomsDetailList extends ArrayAdapter<String> {

    private Activity context;
    private List<String> roomList;
    private TextView first, second , third , fourth;
    private List<String> roomTypeList;

    public RoomsDetailList(Activity context, List<String> roomList, List<String> roomTypeList) {
        super(context, R.layout.room_row, roomList);

        this.context = context;
        this.roomList = roomList;
        this.roomTypeList = roomTypeList;

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms");


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemRoom = inflater.inflate(R.layout.room_row, null, true);
        first = listViewItemRoom.findViewById(R.id.firstTextView);
        second = listViewItemRoom.findViewById(R.id.secondTextView);
        third = listViewItemRoom.findViewById(R.id.thirdTextView);
        fourth = listViewItemRoom.findViewById(R.id.fourthTextView);

        String roomType = roomTypeList.get(position);

        fourth.setText(roomType);

        Log.i("C",position+ ":"+roomType);
        third.setText(roomList.get(position));

        return listViewItemRoom;
    }
}
