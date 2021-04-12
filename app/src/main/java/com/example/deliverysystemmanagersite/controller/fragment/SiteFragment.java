package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.SiteViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SiteFragment extends Fragment {

    private SiteViewModel siteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        siteViewModel =
                new ViewModelProvider(this).get(SiteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_site, container, false);

        final TextView textView = root.findViewById(R.id.text_site);
        siteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
