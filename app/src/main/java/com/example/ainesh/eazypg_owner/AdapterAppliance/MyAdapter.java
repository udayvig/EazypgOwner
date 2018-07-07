package com.example.ainesh.eazypg_owner.AdapterAppliance;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ainesh.eazypg_owner.InterfaceAppliance.ApplianceLoadMore;
import com.example.ainesh.eazypg_owner.ModelAppliance.ModelItem;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;


class LoadingViewHolder extends RecyclerView.ViewHolder
{
    public static ProgressBar progressBar;

     public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar =  itemView.findViewById(R.id.progressBar);

    }
}

class ItemViewHolder extends RecyclerView.ViewHolder
{
    public TextView name;

    public ItemViewHolder(View itemView) {
        super(itemView);
        name=  itemView.findViewById(R.id.applianceNameTextVIew);
    }
}

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

   private final int VIEW_TYPE_ITEM =0, VIEW_TYPE_LOADING=0;

    ApplianceLoadMore loadMore;
    boolean isLoading;
    Activity activity;
    List<ModelItem> items;
    int visibleThreshold =5;
    int lastVisibleItem ,totalItemCount;

    public MyAdapter(RecyclerView recyclerView, Activity activity , List<ModelItem> items)
    {
     this.activity= activity;
     this.items=items;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem= linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold))
                {
                    if(loadMore != null)
                        loadMore.onLoadMore();
                    isLoading=true;
                }

            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ApplianceLoadMore loadMore)
    {
        this.loadMore=loadMore;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if(viewType == VIEW_TYPE_ITEM)
        {
            View view = LayoutInflater.from(activity).inflate(R.layout.applianceitem,parent,false);
            return new ItemViewHolder(view);
        }
        else if (viewType == VIEW_TYPE_LOADING)
        {
            View view = LayoutInflater.from(activity).inflate(R.layout.applianceitem,parent,false);
            return new ItemViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ItemViewHolder)
        {
            ModelItem item = items.get(position);
            ItemViewHolder viewHolder= (ItemViewHolder) holder;
            if(items.get(position)!= null)
            {
            viewHolder.name.setText(items.get(position).getName());
            }
            else
            {
                viewHolder.name.setText("  ");
            }
           // viewHolder.
        }
        else if(holder instanceof LoadingViewHolder)
        {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder)holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setLoaded()
    {
        isLoading= false;
    }


}
