package com.example.deliverysystemmanagersite.adapter.driverAdpter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.Packages;

import java.util.List;

import androidx.annotation.NonNull;

public class DriverWorkListAdapter extends ArrayAdapter<Packages> {
    private int resourceId;

    public DriverWorkListAdapter(@NonNull Context context, int resource, List<Packages> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Packages packages = getItem(position); //获取当前项的package实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView OrderNum = (TextView) view.findViewById(R.id.Id);
        TextView Driver = (TextView) view.findViewById(R.id.Package_Driver_Id);
        TextView Vendor = (TextView) view.findViewById(R.id.Departure);
        TextView Destination = (TextView) view.findViewById(R.id.Destination);
        TextView Status = (TextView) view.findViewById(R.id.Package_Status);

        OrderNum.setText(packages.getPackageId()+"");

        Driver.setText(packages.getDriver());
        Vendor.setText(packages.getDeparture());
        Destination.setText(packages.getDestination());

        String state = packages.getStringState(packages.getState());
        Status.setText(state);
        if (state.equals("pending")){
            Status.setTextColor(Color.RED);
        } else if (state.equals("delivered")){
            Status.setTextColor(Color.GREEN);
        } else {
            Status.setTextColor(Color.BLUE);
        }
        return view;
    }
}
