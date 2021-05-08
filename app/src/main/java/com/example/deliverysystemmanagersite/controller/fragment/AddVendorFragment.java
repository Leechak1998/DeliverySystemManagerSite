package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import org.json.JSONException;
import org.json.JSONObject;


public class AddVendorFragment extends Fragment {
    private View root;
    private EditText et_vendorname;
    private EditText et_address;
    private EditText et_email;
    private EditText et_phone;
    private Button btn_submit;
    private Button btn_cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Add new vendor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_add_vendor, container, false);

        init();
        return root;
    }

    private void init() {
        et_vendorname = (EditText) root.findViewById(R.id.etSitename);
        et_address = (EditText) root.findViewById(R.id.etAddress);
        et_email = (EditText) root.findViewById(R.id.etEmail);
        et_phone = (EditText) root.findViewById(R.id.etPhone);
        btn_submit = (Button) root.findViewById(R.id.btnSub);
        btn_cancel = (Button) root.findViewById(R.id.btnCan);

        btn_submit.setOnClickListener(view -> new Thread(() -> {

            HttpConnectionUtil htc = new HttpConnectionUtil();
            String test = htc.doGet("http://10.0.2.2:8339/");
            try {
                JSONObject jsonObject = new JSONObject(test);
                String um = jsonObject.getString("username");
                System.out.println("username:" + um);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println("------" + test);

        }).start());
    }
}