package com.example.deliverysystemmanagersite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.Site;

import java.util.List;

public class SiteAdapter extends ArrayAdapter<Site> {
    private  int resourceId;
    public SiteAdapter(@NonNull Context context, int resource, List<Site> object) {
        super(context, resource,object);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Site site = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView name = (TextView) view.findViewById(R.id.site_name);
        TextView id = (TextView) view.findViewById(R.id.site_id);
        TextView address = (TextView) view.findViewById(R.id.site_address);
//        TextView admin = (TextView) view.findViewById(R.id.site_admin);
        TextView email = (TextView) view.findViewById(R.id.site_email);
        TextView tel = (TextView) view.findViewById(R.id.site_tel);

        name.setText(site.getSite_name());
        id.setText(site.getSite_id()+"");
        address.setText(site.getAddress());
//        admin.setText(site.getAdmin());
        email.setText(site.getEmail());
        tel.setText(site.getTel());
        return view;
    }
}
