package com.example.deliverysystemmanagersite.driver.driver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.deliverysystemmanagersite.R;

public class DriverMineFragment extends Fragment {
    private View root;
    private Button btn_modify;

    public static DriverMineFragment newInstance(String param1, String param2) {
        DriverMineFragment fragment = new DriverMineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_driver_mine, container, false);

        init();

        return root;
    }

    private void init() {
        btn_modify = (Button) root.findViewById(R.id.btn_modify);

        btn_modify.setOnClickListener(view -> {
            //
        });
    }
}