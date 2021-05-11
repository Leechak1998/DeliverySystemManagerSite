package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.adapter.DriverAdapter;
import com.example.deliverysystemmanagersite.model.Driver;
import com.example.deliverysystemmanagersite.model.DriverViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


public class DriverFragment extends Fragment {

    private View root;
    private DriverViewModel driverViewModel;
    private ListView listView;
    private DriverFragment fra;
    private List<Driver> pList;

    public static DriverFragment newInstance() {
        DriverFragment fragment = new DriverFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_driver, container, false);

        init();
        setListener();
        driverViewModel = new ViewModelProvider(requireActivity()).get(DriverViewModel.class);
        return root;
    }


    public void init(){
        listView = (ListView) root.findViewById(R.id.Driver_Lv);

        driverViewModel = new DriverViewModel();
        pList = new ArrayList<>();
        pList = driverViewModel.getList();
        listView.setAdapter(new DriverAdapter(getActivity(), R.layout.driver_item, pList));

        fra = this;
    }
    private void setListener() {


        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            driverViewModel.selectDriver(pList.get(i));

            //Navigation.findNavController(root).navigate(R.id.navigation_DriverDetail);
            NavHostFragment.findNavController(fra).navigate(R.id.navigation_DriverDetail);

        });

    }
}