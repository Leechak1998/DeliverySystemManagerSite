package com.example.deliverysystemmanagersite.controller.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.adapter.PageAdapter;
import com.google.android.material.tabs.TabLayout;


public class WorkListFragment extends Fragment {
    private View root;
    public ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_work_list, container, false);
        viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(new PageAdapter(this.getActivity(),getChildFragmentManager()));

        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setBackgroundColor(Color.WHITE);
        tabs.setTabTextColors(Color.BLACK,Color.GRAY);
        tabs.setupWithViewPager(viewPager);

        return root;
    }


}