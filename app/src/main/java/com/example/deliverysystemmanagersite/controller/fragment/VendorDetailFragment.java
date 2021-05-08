package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.Vendor;
import com.example.deliverysystemmanagersite.model.VendorViewModel;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import java.util.List;

public class VendorDetailFragment extends Fragment {

    private TextView vendor_name;
    private TextView tel;
    private TextView vendor_id;
    private TextView email;
    private TextView address;
    private VendorViewModel vendorViewModel;
    private List<Vendor> vendor_List;
    private Vendor vendor_selected;
    private ImageButton Edit;
    private View root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            vendorViewModel = new ViewModelProvider(this).get(VendorViewModel.class);
            vendor_List = vendorViewModel.getText();
            vendor_selected = vendor_List.get(getArguments().getInt("index"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_vendor_detail, container, false);
        init();
        setListener();
        return root;
    }

    public void init(){
        vendor_name = (TextView) root.findViewById(R.id.Vendor_Name);
        tel = (TextView) root.findViewById(R.id.Vendor_Tel);
        vendor_id = (TextView) root.findViewById(R.id.Vendor_Id);
        email = (TextView) root.findViewById(R.id.Vendor_Email);
        address = (TextView) root.findViewById(R.id.Vendor_Address);

        Edit = (ImageButton) root.findViewById(R.id.Vendor_edit);

        vendor_name.setText(vendor_selected.getVendor_name());
        tel.setText(vendor_selected.getTel());
        vendor_id.setText(vendor_selected.getVendor_id()+"");
        email.setText(vendor_selected.getEmail());
        address.setText(vendor_selected.getAddress());
    }
    public void setListener(){
        Edit.setOnClickListener(view->{
            HttpConnectionUtil htc = new HttpConnectionUtil();
            String Tel = tel.getText().toString();
            String Vendor_name = vendor_name.getText().toString();
            String Email = email.getText().toString();
            String Vendor_id = vendor_id.getText().toString();
            String Address = address.getText().toString();
            Vendor v = new Vendor(Vendor_name,Tel,Integer.parseInt(Vendor_id),Email,Address);
            htc.doGet("http://10.0.2.2:8339/updateVendor?vanderName=" + v.getVendor_name() +"&tel="+v.getTel()+ "&vendorId=" + v.getVendor_id() + "&email="+v.getEmail()+"&address="+v.getAddress());
            tel.setText(Tel);
            vendor_name.setText(Vendor_name);
            email.setText(Email);
            address.setText(Address);
        });
    }
}