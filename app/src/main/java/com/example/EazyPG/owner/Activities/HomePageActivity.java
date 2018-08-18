package com.example.EazyPG.owner.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.ImageView;

import com.example.EazyPG.owner.Adapter.MyAdapter;
import com.example.EazyPG.owner.Model.Item;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomePageActivity extends AppCompatActivity {

    ImageView yourPG;
    ImageView staff;
    ImageView tenant;
    ImageView expense;
    ImageView passbook;
    ImageView complaint;

    SwitchCompat logout;

    List<Item> items = new ArrayList<>();
    MyAdapter adapter;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        yourPG = findViewById(R.id.yourPgImageView);
        staff = findViewById(R.id.staffImageView);
        tenant = findViewById(R.id.tenantImageView);
        expense = findViewById(R.id.foodImageView);
        passbook = findViewById(R.id.passbookImageView);
        logout = findViewById(R.id.logoutButton);
        complaint = findViewById(R.id.complaintImageView);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseMessaging.getInstance().subscribeToTopic("pushNotifications");

        tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, TenantActivity.class));
                finish();
            }
        });

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, StaffActivity.class));
                finish();
            }
        });

        yourPG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, MyPGActivity.class));
                finish();
            }
        });

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, ExpenseActivity.class));
                finish();
            }
        });

        passbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, PassbookActivity.class));
                finish();
            }
        });

        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, ComplaintActivity.class));
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(HomePageActivity.this).setTitle("Logout")
                        .setMessage("\nAre you sure you want to logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                firebaseAuth.signOut();
                                startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
                                finish();
                                logout.setChecked(false);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logout.setChecked(true);
                            }
                        })
                        .setIcon(R.drawable.ic_warning_black_24dp)
                        .show();
            }
        });

        //random data
        randomnotificationData();
        /*RecyclerView recycler = findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapter(recycler, this, items);
        recycler.setAdapter(adapter);



        //Set Load more events
        adapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if(items.size()<=10){
                    items.add(null);
                    adapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            adapter.notifyItemRemoved(items.size());

                            //More random data
                            int index =items.size();
                            int end= index;
                            for(int i =index; i <end; i++)
                            {
                                String name = UUID.randomUUID().toString();
                                Item item = new Item(name , name.length());
                                items.add(item);
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    },100);
                }
                else
                {
                    Toast.makeText(HomePageActivity.this,"DATA LOAD COMPLETED",Toast.LENGTH_SHORT).show();
                }
            }
        });*/


    }

    private  void randomnotificationData()
    {
        for(int i =0; i<10;i++)
        {
            String name = UUID.randomUUID().toString();
            Item item= new Item (name,name.length());
            items.add(item);
        }
    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}