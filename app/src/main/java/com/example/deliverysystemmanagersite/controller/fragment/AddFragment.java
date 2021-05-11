package com.example.deliverysystemmanagersite.controller.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.deliverysystemmanagersite.R;

import java.util.ArrayList;
import java.util.List;

public class AddFragment extends Fragment {
    private View root;
    private Button btnPackages;
    private Button btnDriver;
    private Button btnVendor;
    private Button btnSite;
    private AddPackagesFragment addPackagesFragment;
    private AddDriverFragment addDriverFragment;
    private AddVendorFragment addVendorFragment;
    private AddSiteFragment addSiteFragment;
    private Fragment fra;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_add, container, false);

        init();
        setListener();
        return root;
    }

    private void init(){
        fra = this;
        btnPackages = (Button) root.findViewById(R.id.btnPackage);
        btnDriver = (Button) root.findViewById(R.id.btnDriver);
        btnVendor = (Button) root.findViewById(R.id.btnVendor);
        btnSite = (Button) root.findViewById(R.id.vtnSite);

        addPackagesFragment = new AddPackagesFragment();
        addDriverFragment = new AddDriverFragment();
        addVendorFragment = new AddVendorFragment();
        addSiteFragment = new AddSiteFragment();

        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.dif_add_fragment, addPackagesFragment).commitAllowingStateLoss();
    }

    private void setListener() {
        btnPackages.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dif_add_fragment,addPackagesFragment)
                    .commit();
        });

        btnDriver.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dif_add_fragment, addDriverFragment)
                    .commit();
        });

        btnVendor.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dif_add_fragment, addVendorFragment)
                    .commit();
        });

        btnSite.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dif_add_fragment, addSiteFragment)
                    .commit();
        });
    }

}