package com.example.deliverysystemmanagersite.controller.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.db.ConnectDb;
import com.example.deliverysystemmanagersite.driver.driver.DriverWorkListViewModel;
import com.example.deliverysystemmanagersite.model.PackageViewModel;
import com.example.deliverysystemmanagersite.model.Packages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class PackageDetailsFragment extends Fragment {

    private PackageViewModel packageViewModel;
    private List<Packages> package_List;
    private Packages package_selected;
    private TextView departure;
    private TextView destination;
    private TextView driver;
    private TextView vendor;
    private TextView delivery_date;
    private TextView state;
    private TextView packageId;
    private View root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_package_details, container, false);

        packageViewModel = new ViewModelProvider(requireActivity()).get(PackageViewModel.class);
        packageViewModel.getSelectedPackage().observe(getViewLifecycleOwner(), item -> {
//            TextView textView = (TextView) root.findViewById(R.id.tv);
//            textView.setText(viewModel.getSelected().getValue());
            init();
            departure.setText(packageViewModel.getSelectedPackage().getValue().getDeparture());
            destination.setText(packageViewModel.getSelectedPackage().getValue().getDestination());
            driver.setText(packageViewModel.getSelectedPackage().getValue().getDriver());
            packageId.setText(packageViewModel.getSelectedPackage().getValue().getPackageId()+"");
            vendor.setText(packageViewModel.getSelectedPackage().getValue().getVendor());
            delivery_date.setText(packageViewModel.getSelectedPackage().getValue().getDate());
            state.setText(packageViewModel.getSelectedPackage().getValue().getStringState(packageViewModel.getSelectedPackage().getValue().getState()));

        });

        return root;
    }

    public void init(){
        departure = (TextView) root.findViewById(R.id.departure);
        destination = (TextView) root.findViewById(R.id.destination);
        driver = (TextView) root.findViewById(R.id.driver);
        vendor = (TextView) root.findViewById(R.id.vendor);
        delivery_date = (TextView) root.findViewById(R.id.deliver_date);
        state = (TextView) root.findViewById(R.id.status);
        packageId = (TextView) root.findViewById(R.id.packageId);
    }
}