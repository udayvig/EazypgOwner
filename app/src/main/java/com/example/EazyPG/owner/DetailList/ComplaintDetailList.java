package com.example.EazyPG.owner.DetailList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.EazyPG.owner.DetailsClasses.ComplaintDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ComplaintDetailList extends RecyclerView.Adapter<ComplaintDetailList.MyViewHolder>{

    FirebaseStorage storage = FirebaseStorage.getInstance();

    List<ComplaintDetails> complaintDetailsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView complaintImageView;
        public TextView complaintIDTextView;
        public TextView statusTextView;
        public TextView availabilityTextView;
        public TextView descriptionTextView;
        public TextView secondLevelTextView;
        public TextView thirdLevelTextView;
        public TextView firstLevelTextView;
        public TextView nameTextView;
        public TextView roomNoTextView;

        public MyViewHolder(View view) {
            super(view);

            nameTextView = view.findViewById(R.id.nameTextView);
            roomNoTextView = view.findViewById(R.id.roomNoTextView);
            complaintImageView = view.findViewById(R.id.complaintImageView);
            complaintIDTextView = view.findViewById(R.id.complaintIDTextView);
            statusTextView = view.findViewById(R.id.statusTextView);
            availabilityTextView = view.findViewById(R.id.availabilityTextView);
            descriptionTextView = view.findViewById(R.id.descriptionTextView);
            secondLevelTextView = view.findViewById(R.id.secondLevelTextView);
            thirdLevelTextView = view.findViewById(R.id.thirdLevelTextView);
            firstLevelTextView = view.findViewById(R.id.firstLevelTextView);
        }
    }

    public ComplaintDetailList(List<ComplaintDetails> complaintDetailsList) {
        this.complaintDetailsList = complaintDetailsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Log.e("complaintapturedetailis", complaintDetailsList.size() + "");
        Log.e("complaintPos", position + "");

        ComplaintDetails complaintDetails1 = complaintDetailsList.get(position);

        StorageReference storageReference = storage.getReferenceFromUrl("gs://eazypgowner.appspot.com").child("Complaints").child(complaintDetails1.imageName);

        final long ONE_MEGABYTE = 1024 * 1024;

        storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.complaintImageView.setImageBitmap(bitmap);
            }
        });

        holder.nameTextView.setText(complaintDetails1.name);
        holder.roomNoTextView.setText(complaintDetails1.roomNo);
        holder.complaintIDTextView.setText(complaintDetails1.complaintId);
        holder.statusTextView.setText(complaintDetails1.status);
        holder.availabilityTextView.setText(complaintDetails1.availabilityTime);
        holder.descriptionTextView.setText(complaintDetails1.description);
        holder.firstLevelTextView.setText(complaintDetails1.firstLevel);
        holder.secondLevelTextView.setText(complaintDetails1.secondLevel);
        holder.thirdLevelTextView.setText(complaintDetails1.thirdLevel);
    }

    @Override
    public int getItemCount() {
        return complaintDetailsList.size();
    }

}