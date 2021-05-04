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
import com.example.deliverysystemmanagersite.model.Driver;
import com.example.deliverysystemmanagersite.model.DriverViewModel;

import java.util.List;

public class DriverDetailsFragment extends Fragment {
    private View root;
    private TextView driver_name;
    private TextView tel;
    private TextView driver_id;
    private TextView email;
    private DriverViewModel driverViewModel;
    private List<Driver> driver_List;
    private Driver driver_selected;
    private ImageButton Edit;


    public static DriverDetailsFragment newInstance(String param1, String param2) {
        DriverDetailsFragment fragment = new DriverDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            driverViewModel = new ViewModelProvider(this).get(DriverViewModel.class);
            driver_List = driverViewModel.getText();
            driver_selected = driver_List.get(getArguments().getInt("index"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_driver_details, container, false);
        init();
        setListener();
        return root;
    }

    public void init(){
        driver_name = (TextView) root.findViewById(R.id.driver_name);
        tel = (TextView) root.findViewById(R.id.driver_tel);
        driver_id = (TextView) root.findViewById(R.id.driver_id);
        email = (TextView) root.findViewById(R.id.driver_email);

        Edit = (ImageButton) root.findViewById(R.id.Site_edit);

        driver_name.setText(driver_selected.getDriver_name());
        tel.setText(driver_selected.getTel());
        driver_id.setText(driver_selected.getDriver_id()+"");
        email.setText(driver_selected.getEmail());
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