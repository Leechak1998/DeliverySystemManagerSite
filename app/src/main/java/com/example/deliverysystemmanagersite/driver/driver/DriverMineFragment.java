package com.example.deliverysystemmanagersite.driver.driver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.deliverysystemmanagersite.R;

public class DriverMineFragment extends Fragment {

    private Button btnOut;
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
    }

    private void setListener(){
        btnOut.setOnClickListener(view->{
            System.out.println("exit");
            System.exit(0);
        });
    }
}