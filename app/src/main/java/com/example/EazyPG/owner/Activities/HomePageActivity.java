package com.example.EazyPG.owner.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.EazyPG.owner.Adapter.MyAdapter;
import com.example.EazyPG.owner.Model.Item;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;

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
    ImageView notifCard;
    TextView notifCardText;
    ImageView notifCard2;
    TextView notifCardText2;
    FloatingActionButton bt1;
    FloatingActionButton bt2;
    FloatingActionButton bt3;
    FloatingActionButton bt4;
    FloatingActionButton bt5;
    NestedScrollView nestedScrollView;
    //TextView bt1Text;

    private  int someVarA;
    private  String someVarB;

    SwitchCompat logout;

    List<Item> items = new ArrayList<>();
    MyAdapter adapter;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(0,0,0));
        }

        yourPG = findViewById(R.id.yourPgImageView);
        staff = findViewById(R.id.staffImageView);
        notifCard = findViewById(R.id.notifCardImageView);
        notifCardText = findViewById(R.id.notifTextView);
        tenant = findViewById(R.id.tenantImageView);
        expense = findViewById(R.id.foodImageView);
        passbook = findViewById(R.id.passbookImageView);
        logout = findViewById(R.id.logoutButton);
        complaint = findViewById(R.id.complaintImageView);
        bt1 = findViewById(R.id.goneFab1);
        bt2 = findViewById(R.id.goneFab2);
        bt3 = findViewById(R.id.goneFab3);
        bt4 = findViewById(R.id.goneFab4);
        bt5 = findViewById(R.id.goneFab5);
        nestedScrollView = findViewById(R.id.nestedScroll);
        // bt1Text = findViewById(R.id.goneFab1text);

        final AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.appBar);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    notifCard.setVisibility(View.INVISIBLE);
                    notifCardText.setVisibility(View.INVISIBLE);
                    bt1.show();
                    bt2.show();
                    bt3.show();
                    bt4.show();
                    bt5.show();
                    //nestedScrollView.setBackgroundColor(Color.BLACK);
                    // bt1Text.setVisibility(View.VISIBLE);
                    // Collapsed
                } else if (verticalOffset == 0) {
                    // Expanded
                    notifCard.setVisibility(View.VISIBLE);
                    notifCardText.setVisibility(View.VISIBLE);
                    bt1.hide();
                    bt2.hide();
                    bt3.hide();
                    bt4.hide();
                    bt5.hide();
                    // nestedScrollView.setBackgroundColor(Color.WHITE);
                    //  bt1Text.setVisibility(View.INVISIBLE);
                } else {
                    // Somewhere in between
                    notifCardText.setVisibility(View.INVISIBLE);
                    notifCard.setVisibility(View.INVISIBLE);
                    bt1.hide();
                    bt2.hide();
                    bt3.hide();
                    bt4.hide();
                    bt5.hide();
                    //nestedScrollView.setBackgroundColor(Color.WHITE);
                    // bt1Text.setVisibility(View.INVISIBLE);
                }
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();

        tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, TenantActivity.class));
                finish();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, TenantActivity.class));
                finish();
                //  appBarLayout.setExpanded(true);
            }
        });

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, StaffActivity.class));
                finish();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
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

        bt4.setOnClickListener(new View.OnClickListener() {
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

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        bt5.setOnClickListener(new View.OnClickListener() {
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("someVarA", someVarA);
        outState.putString("someVarB", someVarB);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        someVarA = savedInstanceState.getInt("someVarA");
        someVarB = savedInstanceState.getString("someVarB");
    }

}