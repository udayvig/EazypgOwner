package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EazyPG.owner.DetailsClasses.PaymentDetails;
import com.example.ainesh.eazypg_owner.R;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import java.util.Date;
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
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewPayment = inflater.inflate(R.layout.payment_row, null, true);
        final PaymentDetails paymentDetails = paymentList.get(position);

        final String payId = paymentDetails.getPayId();

        final TextView first = listViewPayment.findViewById(R.id.firstTextViewPayment);
        final TextView second = listViewPayment.findViewById(R.id.secondTextViewPayment);
        final TextView third = listViewPayment.findViewById(R.id.thirdTextViewPayment);

        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    RazorpayClient razorpayClient = null;
                    try {
                        razorpayClient = new RazorpayClient("rzp_test_sAUX9CKy38r5OA", "qgqr52bFjPFQhE9AZDcs2UGp");
                        Payment payment = razorpayClient.Payments.fetch(payId);
                    } catch (RazorpayException e) {
                        e.printStackTrace();
                    }

                    synchronized (this) {
                        wait(1000);

                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    int amount = payment.get("amount");
                                    String id = payment.get("id");
                                    Date createdAt = payment.get("created_at");

                                    first.setText(Integer.toString(amount));
                                    second.setText(createdAt.toString());
                                    third.setText(id);

                                } catch (RazorpayException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                    }

                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();*/

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    RazorpayClient client = new RazorpayClient("rzp_test_sAUX9CKy38r5OA", "qgqr52bFjPFQhE9AZDcs2UGp");
                    final Payment payment = client.Payments.fetch(payId);
                    final int amount = payment.get("amount");
                    final String id = payment.get("id");
                    final Date createdAt = payment.get("created_at");
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            first.setText(Integer.toString(amount));
                            second.setText(createdAt.toString());
                            third.setText(id);
                        }
                    });
                }catch(RazorpayException e){
                    e.printStackTrace();
                }
            }
        });

        return listViewPayment;
    }


}
