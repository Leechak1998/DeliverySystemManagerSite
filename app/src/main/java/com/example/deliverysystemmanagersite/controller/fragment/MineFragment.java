package com.example.deliverysystemmanagersite.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.deliverysystemmanagersite.MainActivity;
import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.util.ActivityCollectorUtil;

public class MineFragment extends Fragment {

    private Button btnOut,btnModifyPassword,btnMoreInfo,btnAboutUs;
    private View root;
    private ActivityCollectorUtil collectorUtil;



    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_mine, container, false);
        Init();
        btnListener();
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void Init(){
        btnOut = (Button) root.findViewById(R.id.btnOut);
        btnModifyPassword = (Button) root.findViewById(R.id.btnModifyPassword);
        btnAboutUs = (Button) root.findViewById(R.id.btnAboutUs);
        btnMoreInfo = (Button) root.findViewById(R.id.btnMoreInfo);
    }

    private void btnListener(){
        btnOut.setOnClickListener(view->{
            System.out.println("logout");
            collectorUtil.finishAllActivity();
        });
        btnMoreInfo.setOnClickListener(v -> {

        });
        btnAboutUs.setOnClickListener(v -> {

        });

        btnModifyPassword.setOnClickListener(v -> {

        });
    }
}