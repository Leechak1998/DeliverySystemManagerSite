package com.example.deliverysystemmanagersite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.Driver;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static java.security.AccessController.getContext;

public class driverAdapter extends ArrayAdapter<Driver> {
    private int resourceId;

    public driverAdapter(@NonNull Context context, int resource, List<Driver> object) {
        super(context, resource, object);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Driver driver = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView name = (TextView) view.findViewById(R.id.Name);
        TextView id = (TextView) view.findViewById(R.id.Id);
        TextView tel = (TextView) view.findViewById(R.id.Tel);
        TextView email = (TextView) view.findViewById(R.id.Email);

        System.out.println("输出driver1："+driver.getDriver_id()+"  "+driver.getDriver_name());

        name.setText(driver.getDriver_name());
        id.setText(driver.getDriver_id()+"");
        tel.setText(driver.getTel());
        email.setText(driver.getEmail());

        return view;
    }
}
