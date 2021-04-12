package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.db.ConnectDb;

public class PackageDetailsFragment extends Fragment {
    private View view;
    private ConnectDb connectDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_package_details, container, false);

        String text = getArguments().getString("text");
        TextView textView = (TextView) view.findViewById(R.id.tv);
        textView.setText(text);

        connectDb = new ConnectDb();
        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(view -> {








        });


        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(view).navigateUp();            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);
        callback.setEnabled(true);




        return view;
    }
}