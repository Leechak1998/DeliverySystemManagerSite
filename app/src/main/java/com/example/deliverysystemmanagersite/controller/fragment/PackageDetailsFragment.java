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
import com.example.deliverysystemmanagersite.model.PackageViewModel;
import com.example.deliverysystemmanagersite.model.Packages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PackageDetailsFragment extends Fragment {

    private PackageViewModel packageViewModel;
    private List<Packages> package_List;
    private Packages package_selected;
    private TextView departure;
    private TextView destination;
    private TextView driver;
    private TextView tel;
    private TextView vendor;
    private TextView delivery_date;
    private TextView state;
    private TextView packageId;
    private View root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            packageViewModel = new ViewModelProvider(this).get(PackageViewModel.class);
            package_List = packageViewModel.getText();
            package_selected = package_List.get(getArguments().getInt("index"));
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_package_details, container, false);
        init();
//        setListener();
        return root;
    }

    public void init(){
        departure = (TextView) root.findViewById(R.id.departure);
        destination = (TextView) root.findViewById(R.id.destination);
        driver = (TextView) root.findViewById(R.id.driver);
        tel = (TextView) root.findViewById(R.id.driver_tel);
        vendor = (TextView) root.findViewById(R.id.vendor);
        delivery_date = (TextView) root.findViewById(R.id.deliver_date);
        state = (TextView) root.findViewById(R.id.status);
        packageId = (TextView) root.findViewById(R.id.packageId);

        departure.setText(package_selected.getDeparture());
        destination.setText(package_selected.getDestination());
        driver.setText(package_selected.getDriver());
        tel.setText(package_selected.getTel());
        vendor.setText(package_selected.getVendor());
        delivery_date.setText(package_selected.getDate());
        state.setText(package_selected.getState());
        packageId.setText(package_selected.getPackageId()+"");
    }
    //实现日历选择日期
//    final Calendar myCalendar = Calendar.getInstance();
//
//    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//        @Override
//
//        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//            myCalendar.set(Calendar.YEAR, year);
//            myCalendar.set(Calendar.MONTH, monthOfYear);
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            updateLabel();
//        }
//
//        private void updateLabel() {
//            String myFormat = "MM/dd/yyyy";
//            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//            delivery_date.setText(sdf.format(myCalendar.getTime()));
//        }
//
//    };
    //
//    private void setListener(){
//        日历选择日期的监听器
//        delivery_date.setOnClickListener(view->{
//            new DatePickerDialog(this.getContext(), date, myCalendar
//                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//        });
//
//    }
}