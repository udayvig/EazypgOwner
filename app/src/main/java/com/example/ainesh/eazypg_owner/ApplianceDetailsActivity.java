package com.example.ainesh.eazypg_owner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.ainesh.eazypg_owner.Adapter.MyAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class ApplianceDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar ;


    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_details);

        toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Appliances");

        recyclerView = findViewById(R.id.detailRecycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        FirebaseRecyclerAdapter<ApplianceDetail, ApplianceViewHolder> detailAdapter
                = new FirebaseRecyclerAdapter<ApplianceDetail, ApplianceViewHolder>(ApplianceDetail.class, R.layout.appliance_row,ApplianceViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(ApplianceViewHolder viewHolder, ApplianceDetail model, int position) {

                viewHolder.setFirst(model.getRoomNo());
                viewHolder.setSecond(model.getBrand());
                viewHolder.setThird(model.getLastServiceDate());

            }
        };

        recyclerView.setAdapter(detailAdapter);
    }

    public static class ApplianceViewHolder extends RecyclerView.ViewHolder {

        TextView firstTextView;
        TextView secondTextView;
        TextView thirdTextView;

        public ApplianceViewHolder(View itemView) {
            super(itemView);

            firstTextView = itemView.findViewById(R.id.firstTextView);
            secondTextView = itemView.findViewById(R.id.secondTextView);
            thirdTextView = itemView.findViewById(R.id.thirdTextView);


        }

        public void setFirst(String roomNo) {

            firstTextView.setText(roomNo);
        }

        public void setSecond(String brand) {

            secondTextView.setText(brand);
        }

        public void setThird(String lastServiceDate) {

            thirdTextView.setText(lastServiceDate);
        }
    }
}
