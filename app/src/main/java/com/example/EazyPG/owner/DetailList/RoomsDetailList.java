package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Toast;

import com.example.EazyPG.owner.Activities.RoomApplianceDetailsActivity;
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

    public static final String EXTRA_MESSAGE = "com.example.EazyPG.owner";

    private Button applianceButton, tenantButton;

    public RoomsDetailList(Activity context, List<String> roomList, List<String> roomTypeList) {
        super(context, R.layout.room_row, roomList);

        this.context = context;
        this.roomList = roomList;
        this.roomTypeList = roomTypeList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater inflater = context.getLayoutInflater();
        final View listViewItemRoom = inflater.inflate(R.layout.room_row, null, true);

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

        return listViewItemRoom;
    }
}
