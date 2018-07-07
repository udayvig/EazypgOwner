package com.example.ainesh.eazypg_owner;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import com.example.ainesh.eazypg_owner.AdapterAppliance.MyAdapter;
import com.example.ainesh.eazypg_owner.InterfaceAppliance.ApplianceLoadMore;
import com.example.ainesh.eazypg_owner.ModelAppliance.ModelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApplianceActivity extends AppCompatActivity {

    private Toolbar toolbar ;
    private List<ModelItem> items = new ArrayList<>();
    private MyAdapter adapter;
    private Button addBtn ;
    private RecyclerView applianceRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance);

        toolbar =  findViewById(R.id.applianceToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        addBtn=findViewById(R.id.addMoreBtn);

        randomnotificationData();
        RecyclerView applianceRecycler= (RecyclerView) findViewById(R.id.applianceRecycler);

        applianceRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapter(applianceRecycler, this, items);
        applianceRecycler.setAdapter(adapter);



        //Set Load more events
        adapter.setLoadMore(new ApplianceLoadMore() {
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
                                ModelItem item = new ModelItem(name , name.length());
                                items.add(item);
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    },100);
                }
                else
                {
                    Toast.makeText(ApplianceActivity.this,"DATA LOAD COMPLETED",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private  void randomnotificationData()
    {
        for(int i =0; i<10;i++)
        {
            String name = UUID.randomUUID().toString();
            ModelItem item= new ModelItem(name,name.length());
            items.add(item);
        }
    }
}
