package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.DetailsClasses.PaymentDetails;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class PaymentDetailList extends ArrayAdapter<PaymentDetails>{

    private Activity context;
    private List<PaymentDetails> paymentList;

    public PaymentDetailList(Activity context, List<PaymentDetails> paymentList) {

        super(context, R.layout.payment_row, paymentList);

        this.context = context;
        this.paymentList = paymentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewPayment = inflater.inflate(R.layout.payment_row, null, true);
        final PaymentDetails paymentDetails = paymentList.get(position);

        TextView first = listViewPayment.findViewById(R.id.firstTextView);
        first.setText(paymentDetails.getPayId());

        return listViewPayment;
    }
}
