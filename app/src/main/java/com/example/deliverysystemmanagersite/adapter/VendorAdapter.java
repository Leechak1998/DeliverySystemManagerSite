package com.example.deliverysystemmanagersite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.Vendor;

import java.util.List;

public class VendorAdapter extends ArrayAdapter<Vendor> {
    private int resourceId;
    public VendorAdapter(@NonNull Context context, int resource, List<Vendor> object) {
        super(context, resource, object);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Vendor vendor = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView name = (TextView) view.findViewById(R.id.Site_Name);
        TextView id = (TextView) view.findViewById(R.id.Site_Id);
        TextView address = (TextView) view.findViewById(R.id.Site_Address);
        TextView email = (TextView) view.findViewById(R.id.Site_Email);
        TextView tel = (TextView) view.findViewById(R.id.Site_Tel);

        name.setText(vendor.getVendor_name());
        id.setText(vendor.getVendor_id()+"");
        address.setText(vendor.getAddress());
        email.setText(vendor.getEmail());
        tel.setText(vendor.getTel());
        return view;
    }
}
