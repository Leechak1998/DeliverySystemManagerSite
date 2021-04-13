package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.VendorViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import static android.content.ContentValues.TAG;

public class VendorFragment extends Fragment {

    private VendorViewModel vendorViewModel;

    public static VendorFragment newInstance() {
        VendorFragment fragment = new VendorFragment();
        // Supply num input as an argument.
//        Bundle args = new Bundle();
//        args.putInt("num", num);
//        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vendorViewModel = new ViewModelProvider(this).get(VendorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_vendor, container, false);

        final TextView textView = root.findViewById(R.id.text_vendor);
        vendorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}