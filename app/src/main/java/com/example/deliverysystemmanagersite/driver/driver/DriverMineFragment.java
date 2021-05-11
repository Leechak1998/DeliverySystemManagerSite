package com.example.deliverysystemmanagersite.driver.driver;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.deliverysystemmanagersite.R;

public class DriverMineFragment extends Fragment {

    private Button btnOut;
    private Button btnModiyfy;
    private Button btnMore;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_driver_mine, container, false);
        init();
        setListener();
        return root;
    }
    public void init(){
        btnOut = (Button) root.findViewById(R.id.btnOutDriver);
        btnModiyfy = (Button) root.findViewById(R.id.btn_modify);
        btnMore = (Button) root.findViewById(R.id.btn_more);
    }

    private void setListener(){
        btnOut.setOnClickListener(view->{
            getActivity().finish();
        });

        btnModiyfy.setOnClickListener(view -> {
            Navigation.findNavController(root);
            NavHostFragment.findNavController(this).navigate(R.id.navigation_mine_driver_modify);
        });

        btnMore.setOnClickListener(view -> {
            Navigation.findNavController(root);
            NavHostFragment.findNavController(this).navigate(R.id.navigation_mine_driver_more);
        });
    }
}