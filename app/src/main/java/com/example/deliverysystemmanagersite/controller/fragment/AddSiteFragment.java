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

public class AddSiteFragment extends Fragment {
    private View root;
    private EditText et_sitename;
    private EditText et_address;
    private EditText et_email;
    private EditText et_phone;
    private Button btn_submit;
    private Button btn_cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Add new site");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_add_site, container, false);

        init();

        return root;
    }

    private void init() {
        et_sitename = (EditText) root.findViewById(R.id.etSitename);
        et_address = (EditText) root.findViewById(R.id.etAddress);
        et_email = (EditText) root.findViewById(R.id.etEmail);
        et_phone = (EditText) root.findViewById(R.id.etPhone);
        btn_submit = (Button) root.findViewById(R.id.btnSub);
        btn_cancel = (Button) root.findViewById(R.id.btnCan);

        btn_submit.setOnClickListener(view -> new Thread(() -> {

            String siteName = et_sitename.getText().toString();
            String email = et_email.getText().toString();
            String tel = et_phone.getText().toString();
            String address = et_address.getText().toString();

            HttpConnectionUtil htc = new HttpConnectionUtil();
            System.out.println(htc.doGet("http://10.0.2.2:8339/createSite?siteName=" + siteName +"&email=" + email + "&telephoneNumber=" + tel + "&address=" + address));


        }).start());
    }
}