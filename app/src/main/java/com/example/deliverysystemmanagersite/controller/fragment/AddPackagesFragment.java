package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.adapter.CommentAdapter;
import com.example.deliverysystemmanagersite.model.AddPackagesModel;
import com.example.deliverysystemmanagersite.model.Driver;
import com.example.deliverysystemmanagersite.model.Site;
import com.example.deliverysystemmanagersite.model.Vendor;

import java.util.ArrayList;
import java.util.List;

public class AddPackagesFragment extends Fragment {
    private View root;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private Spinner spinnerDriver;
    private Button btnSub;
    private Button btnCan;

    private List<Vendor> list_departure = new ArrayList<>();
    private List<Site> list_site = new ArrayList<>();
    private List<Driver> list_driver = new ArrayList<>();

    private AddPackagesModel addPackagesModel;
    private CommentAdapter commentAdapter;

    private static int ADD_FROM = 0;
    private static int ADD_TO = 1;
    private static int ADD_DRIVER = 2;

    private Vendor selFrom;
    private Site selTo;
    private Driver selDriver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Add new package");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_add_packages, container, false);

        init();
        setListener();
        return root;
    }

    private void init() {
        spinnerFrom = (Spinner) root.findViewById(R.id.spinnerFrom);
        spinnerTo = (Spinner) root.findViewById(R.id.spinnerTo);
        spinnerDriver = (Spinner) root.findViewById(R.id.spinnerDriver);
        btnSub = (Button) root.findViewById(R.id.btnSub);
        btnCan = (Button) root.findViewById(R.id.btnCan);

        addPackagesModel = new AddPackagesModel();
        commentAdapter = new CommentAdapter();

        list_departure = (List<Vendor>) addPackagesModel.init_list(ADD_FROM);
        list_site = (List<Site>) addPackagesModel.init_list(ADD_TO);
        list_driver = (List<Driver>) addPackagesModel.init_list(ADD_DRIVER);

        spinnerFrom.setAdapter(commentAdapter.setDepartureListAdapter(list_departure, root));
        spinnerTo.setAdapter(commentAdapter.setDestinationListAdapter(list_site, root));
        spinnerDriver.setAdapter(commentAdapter.setDriverListAdapter(list_driver, root));
    }

    private void setListener() {

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //selFrom = listFrom.get(i);
                selFrom = list_departure.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //selTo = listTo.get(i);
                selTo = list_site.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDriver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //selDriver = listDriver.get(i);
                selDriver = list_driver.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSub.setOnClickListener(view -> {
            System.out.println("From: " + selFrom + " To: " + selTo + " By: " + selDriver);
            new Thread(() -> addPackagesModel.sendPackageDataToServer(selFrom, selTo, selDriver,0)).start();
        });

        btnCan.setOnClickListener(view -> {
            //
        });

    }


}