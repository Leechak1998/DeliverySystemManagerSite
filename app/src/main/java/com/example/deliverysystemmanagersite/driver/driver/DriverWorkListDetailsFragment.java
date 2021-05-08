package com.example.deliverysystemmanagersite.driver.driver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.Driver;

public class DriverWorkListDetailsFragment extends Fragment {
    private View root;
    private DriverWorkListViewModel viewModel;

    private EditText et_departure;
    private EditText et_destination;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_id;
    private EditText et_vendor;
    private EditText et_date;
    private EditText et_status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_driver_work_list_details, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(DriverWorkListViewModel.class);
        viewModel.getSelectedPackage().observe(getViewLifecycleOwner(), item -> {
//            TextView textView = (TextView) root.findViewById(R.id.tv);
//            textView.setText(viewModel.getSelected().getValue());
            init();
            System.out.println("----"+viewModel.getSelectedPackage().getValue().getDeparture());

            et_departure.setText(viewModel.getSelectedPackage().getValue().getDeparture());
            et_destination.setText(viewModel.getSelectedPackage().getValue().getDestination());
            et_name.setText(viewModel.getSelectedPackage().getValue().getDriver());
            et_phone.setText(viewModel.getSelectedPackage().getValue().getTel());
            et_id.setText(viewModel.getSelectedPackage().getValue().getPackageId()+"");
            et_vendor.setText(viewModel.getSelectedPackage().getValue().getVendor());
            et_date.setText(viewModel.getSelectedPackage().getValue().getDate());
            et_status.setText(viewModel.getSelectedPackage().getValue().getState());
        });

        return root;
    }

    private void init() {
        et_departure = (EditText) root.findViewById(R.id.et_departure);
        et_destination = (EditText) root.findViewById(R.id.et_destination);
        et_name = (EditText) root.findViewById(R.id.et_name);;
        et_phone = (EditText) root.findViewById(R.id.et_phone);;
        et_id = (EditText) root.findViewById(R.id.et_id);;
        et_vendor = (EditText) root.findViewById(R.id.et_vendor);;
        et_date  = (EditText) root.findViewById(R.id.et_date);;
        et_status = (EditText) root.findViewById(R.id.et_status);;
    }
}