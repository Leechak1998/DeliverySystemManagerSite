package com.example.deliverysystemmanagersite.driver.driver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.controller.activity.MainActivity;
import com.example.deliverysystemmanagersite.db.AppDatabase;
import com.example.deliverysystemmanagersite.model.User;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

public class DriverModifyPwFragment extends Fragment {
    private View root;
    private EditText et_new_pw1;
    private EditText et_new_pw2;
    private Button btn_sub;
    private Button btn_can;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_driver_modify_pw, container, false);
        init();
        return root;
    }

    private void init() {
        et_new_pw1 = (EditText) root.findViewById(R.id.et_new_pw1);
        et_new_pw2 = (EditText) root.findViewById(R.id.et_new_pw2);
        btn_sub = (Button) root.findViewById(R.id.btn_sub);
        btn_can = (Button) root.findViewById(R.id.btn_can);

        btn_sub.setOnClickListener(view -> {
            String pw1 = et_new_pw1.getText().toString();
            String pw2 = et_new_pw2.getText().toString();

            if (pw1.equals("") || pw2.equals("")){
                Toast.makeText(getActivity(), "Please input new password!", Toast.LENGTH_SHORT).show();
            }else {
                if (pw1.equals(pw2)){
                    new Thread(() -> {
                        HttpConnectionUtil htc = new HttpConnectionUtil();

                        int id = Integer.parseInt( htc.doGet("http://10.0.2.2:8339/retrieveDriverId"));
                        htc.doGet("http://10.0.2.2:8339/updateDriverPassword?driverId=" + id + "&password=" + pw1);

                    }).start();
                    Toast.makeText(getActivity(), "success!", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(root);
                    NavHostFragment.findNavController(this).navigate(R.id.navigation_mine_driver);
                }else {
                    Toast.makeText(getActivity(), "Tow passwords are different. Check again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_can.setOnClickListener(view -> {
            Navigation.findNavController(root);
            NavHostFragment.findNavController(this).navigate(R.id.navigation_mine_driver);
        });
    }

}