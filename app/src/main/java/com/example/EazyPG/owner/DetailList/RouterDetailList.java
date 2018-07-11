package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailRouter;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class RouterDetailList extends ArrayAdapter<ApplianceDetailRouter>{

    private Activity context;
    private List<ApplianceDetailRouter> RouterList;

    public RouterDetailList(Activity context, List<ApplianceDetailRouter> RouterList) {
        super(context, R.layout.appliance_row, RouterList);

        this.context = context;
        this.RouterList = RouterList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemRouter = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemRouter.findViewById(R.id.firstTextView);
        TextView second = listViewItemRouter.findViewById(R.id.secondTextView);
        TextView third = listViewItemRouter.findViewById(R.id.thirdTextView);

        ApplianceDetailRouter applianceDetailRouter = RouterList.get(position);

        first.setText(applianceDetailRouter.getRoomNo());
        second.setText(applianceDetailRouter.getBrand());
        third.setText(applianceDetailRouter.getTimeSinceInstallation());

        return listViewItemRouter;
    }

}
