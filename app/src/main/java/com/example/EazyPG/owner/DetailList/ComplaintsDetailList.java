package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.DetailsClasses.ComplaintDetails;
import com.example.EazyPG.owner.DetailsClasses.StaffDetails;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class ComplaintsDetailList extends ArrayAdapter<ComplaintDetails> {

    private Activity context;
    private List<ComplaintDetails> complaintDetailsList;

    public ComplaintsDetailList(Activity context, List<ComplaintDetails> complaintDetailsList) {
        super(context, R.layout.complaint_row, complaintDetailsList);

        this.context = context;
        this.complaintDetailsList = complaintDetailsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemComplaint = inflater.inflate(R.layout.complaint_row, null, true);
        ComplaintDetails complaintDetails = complaintDetailsList.get(position);

        TextView second = listViewItemComplaint.findViewById(R.id.secondLevel);
        TextView third = listViewItemComplaint.findViewById(R.id.thirdLevel);
        TextView name = listViewItemComplaint.findViewById(R.id.nameTextView);
        TextView room = listViewItemComplaint.findViewById(R.id.roomTextView);
        TextView isResolved = listViewItemComplaint.findViewById(R.id.isResolved);
        TextView feedback = listViewItemComplaint.findViewById(R.id.feedback);

        second.setText(complaintDetails.secondLevel);
        third.setText(complaintDetails.thirdLevel);
        name.setText(complaintDetails.name);
        room.setText(complaintDetails.room);
        isResolved.setText(Boolean.toString(complaintDetails.resolved));
        feedback.setText(complaintDetails.feedback);

        return listViewItemComplaint;

    }
}
