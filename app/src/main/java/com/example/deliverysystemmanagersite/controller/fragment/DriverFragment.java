package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.adapter.driverAdapter;
import com.example.deliverysystemmanagersite.model.DriverViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


public class DriverFragment extends Fragment {

    private View root;
    private DriverViewModel driverViewModel;
    private driverAdapter adapter;
    private ListView listView;
    private DriverFragment fra;

    public static DriverFragment newInstance() {
        DriverFragment fragment = new DriverFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        driverViewModel = new ViewModelProvider(this).get(DriverViewModel.class);
        root = inflater.inflate(R.layout.fragment_driver, container, false);

        init();
        setListener();
        return root;
    }
//
//    public void init(){
//        listView = (ListView) root.findViewById(R.id.Driver_Lv);
//        adapter = new driverAdapter(getActivity(), R.layout.driver_item, driverViewModel.getText());
//        listView.setAdapter(adapter);
//
//        fra = this;
//    }

    public void init(){
        listView = (ListView) root.findViewById(R.id.Driver_Lv);
        adapter = new driverAdapter(getActivity(), R.layout.driver_item,driverViewModel.getText());
        listView.setAdapter(adapter);
        fra = this;
    }
    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                Navigation.findNavController(root);
                NavHostFragment
                        .findNavController(fra)
                        .navigate(R.id.navigation_DriverDetail, bundle);

            }
        });
    }
}