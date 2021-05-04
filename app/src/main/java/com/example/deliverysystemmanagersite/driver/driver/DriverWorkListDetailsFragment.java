package com.example.deliverysystemmanagersite.driver.driver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deliverysystemmanagersite.R;

public class DriverWorkListDetailsFragment extends Fragment {
    private View root;
    private DriverWorkListViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_driver_work_list_details, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(DriverWorkListViewModel.class);
        viewModel.getSelected().observe(getViewLifecycleOwner(), item -> {
            TextView textView = (TextView) root.findViewById(R.id.tv);
            textView.setText(viewModel.getSelected().getValue());
        });

        return root;
    }
}