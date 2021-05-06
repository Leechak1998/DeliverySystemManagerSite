package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.AddPackagesModel;

import java.util.ArrayList;
import java.util.List;

public class AddPackagesFragment extends Fragment {
    private View root;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private Spinner spinnerDriver;
    private Button btnSub;
    private Button btnCan;

    private List<String> listFrom = new ArrayList<String>();
    private List<String> listTo = new ArrayList<String>();
    private List<String> listDriver = new ArrayList<String>();

    private AddPackagesModel addPackagesModel;

    private static int ADD_FROM = 0;
    private static int ADD_TO = 1;
    private static int ADD_DRIVER = 2;

    private String selFrom;
    private String selTo;
    private String selDriver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Add new package");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_add_packages, container, false);

        init();
        setListener();
        return root;
    }

    private void init() {
        spinnerFrom = (Spinner) root.findViewById(R.id.spinnerFrom);
        spinnerTo = (Spinner) root.findViewById(R.id.spinnerTo);
        spinnerDriver = (Spinner) root.findViewById(R.id.spinnerDriver);
        btnSub = (Button) root.findViewById(R.id.btnSub);
        btnCan = (Button) root.findViewById(R.id.btnCan);

        addPackagesModel = new AddPackagesModel();
        listFrom = addPackagesModel.initList(ADD_FROM);
        listTo = addPackagesModel.initList(ADD_TO);
        listDriver = addPackagesModel.initList(ADD_DRIVER);

        spinnerFrom.setAdapter(setListAdapter(listFrom));
        spinnerTo.setAdapter(setListAdapter(listTo));
        spinnerDriver.setAdapter(setListAdapter(listDriver));
    }

    private void setListener() {

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selFrom = listFrom.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selTo = listTo.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDriver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selDriver = listDriver.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("From: " + selFrom + " To: " + selTo + " By: " + selDriver);
                /*
                HttpConnectionUtil htc = new HttpConnectionUtil();
                String test = htc.doGet("http://10.0.2.2:8339/addDriverGet?username="+et_username.getText().toString()+"&email="+et_email.getText().toString()+"&phone="+et_phone.getText().toString());
                try {
                    JSONObject jsonObject = new JSONObject(test);
                    String um = jsonObject.getString("username");
                    System.out.println("username:" + um);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("------" + test);

                */
            }
        });
    }

    public ArrayAdapter<String> setListAdapter(List<String> list){
        ArrayAdapter<String> arr_adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return arr_adapter;
    }


}