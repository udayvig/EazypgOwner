package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailAC;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class PaymentDetailList extends ArrayAdapter<String>{

    private Activity context;
    private List<String> paymentList;

    public PaymentDetailList(Activity context, List<String> paymentList) {

        super(context, R.layout.payment_row, paymentList);

        this.context = context;
        this.paymentList = paymentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewId = inflater.inflate(R.layout.payment_row, null, true);
        final String id = paymentList.get(position);



        return super.getView(position, convertView, parent);
    }
}
