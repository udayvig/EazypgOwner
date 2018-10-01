package com.eazypg.EazyPG.owner.DetailList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eazypg.EazyPG.owner.DetailsClasses.CashflowDetails;
import com.eazypg.ainesh.eazypg_owner.R;

import java.util.List;

public class IncomePassbookDetailList extends RecyclerView.Adapter<IncomePassbookDetailList.MyHolder>{

    List<CashflowDetails> incomePassbookDetailList;
    Context context;

    public IncomePassbookDetailList(List<CashflowDetails> incomePassbookDetailList, Context context) {
        this.incomePassbookDetailList = incomePassbookDetailList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return incomePassbookDetailList.size();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.passbook_row, parent, false);

        return new IncomePassbookDetailList.MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        CashflowDetails cashflowDetails = incomePassbookDetailList.get(position);

        holder.amountTextView.setText(cashflowDetails.amount);
        holder.categoryTextView.setText(cashflowDetails.category);
        holder.timestampTextView.setText(cashflowDetails.date);
        holder.paidTextView.setText(cashflowDetails.paidBy);

        holder.inflowOrOutflowTextView.setVisibility(View.GONE);
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        public TextView amountTextView, timestampTextView, inflowOrOutflowTextView, categoryTextView, paidTextView;

        public MyHolder(View itemView){
            super(itemView);

            amountTextView = itemView.findViewById(R.id.amountTextView);
            timestampTextView = itemView.findViewById(R.id.timestampTextView);
            inflowOrOutflowTextView = itemView.findViewById(R.id.inflowOrOutflowTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            paidTextView = itemView.findViewById(R.id.paidForTextView);
        }
    }
}
