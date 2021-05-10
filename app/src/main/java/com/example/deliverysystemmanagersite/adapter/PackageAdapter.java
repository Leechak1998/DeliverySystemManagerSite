package com.example.deliverysystemmanagersite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.Packages;

import java.util.List;

import androidx.annotation.NonNull;

public class PackageAdapter extends ArrayAdapter<Packages>{
    private int resourceId;

    public PackageAdapter(@NonNull Context context, int resource, List<Packages> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Packages packages = getItem(position); //获取当前项的package实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView OrderNum = (TextView) view.findViewById(R.id.Id);
        TextView Status = (TextView) view.findViewById(R.id.Package_Status);
        TextView Vendor = (TextView) view.findViewById(R.id.Departure);
        TextView Destination = (TextView) view.findViewById(R.id.Destination);
        TextView DriverId = (TextView) view.findViewById(R.id.Package_Driver_Id);

        OrderNum.setText(packages.getPackageId()+"");
        Status.setText(packages.getStringState(packages.getState()));
        Vendor.setText(packages.getDeparture());
        Destination.setText(packages.getDestination());
        DriverId.setText(packages.getDriver());

        return view;
    }
}
