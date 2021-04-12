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
import androidx.recyclerview.widget.RecyclerView;

public class packageAdapter extends ArrayAdapter<Packages>{
    private int resourceId;

    public packageAdapter(@NonNull Context context, int resource, List<Packages> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Packages packages = getItem(position); //获取当前项的package实例

        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView OrderNum = (TextView) view.findViewById(R.id.tvId);
        TextView Driver = (TextView) view.findViewById(R.id.tvDriver);
        TextView Vendor = (TextView) view.findViewById(R.id.tvVendor);
        TextView Destination = (TextView) view.findViewById(R.id.tvDestination);

        OrderNum.setText("Order: " + packages.getPackageId());
        Driver.setText(packages.getDriver());
        Vendor.setText(packages.getVendor());
        Destination.setText(packages.getDestination());

        return view;
    }
}
