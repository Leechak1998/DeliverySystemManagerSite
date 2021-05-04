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

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VendorDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VendorDetailFragment extends Fragment {

    private TextView vendor_name;
    private TextView tel;
    private TextView vendor_id;
    private TextView email;
    private TextView vendor_address;
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
        vendor_address = (TextView) root.findViewById(R.id.Vendor_Address);

        Edit = (ImageButton) root.findViewById(R.id.Vendor_edit);

        vendor_name.setText(vendor_selected.getVendor_name());
        tel.setText(vendor_selected.getTel());
        vendor_id.setText(vendor_selected.getVendor_id()+"");
        email.setText(vendor_selected.getEmail());
        vendor_address.setText(vendor_selected.getAddress());
    }
    public void setListener(){
        Edit.setOnClickListener(view->{
            String t = tel.getText().toString();
            if(TextUtils.isEmpty(t)){
                System.out.println("err");
            }else {
                System.out.println("t");
            }
        });
    }
}