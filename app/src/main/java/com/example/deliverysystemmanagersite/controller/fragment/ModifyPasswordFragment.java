package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deliverysystemmanagersite.controller.activity.MainActivity;
import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.db.AppDatabase;
import com.example.deliverysystemmanagersite.model.User;

public class ModifyPasswordFragment extends Fragment {
    private View root;
    private EditText et_new_pw1;
    private EditText et_new_pw2;
    private Button btn_sub;
    private Button btn_can;
    private AppDatabase db;
    private int uID;

    public static ModifyPasswordFragment newInstance(String param1, String param2) {
        ModifyPasswordFragment fragment = new ModifyPasswordFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_modify_password, container, false);
        init();
        return root;
    }

    private void init() {
        db = MainActivity.mdb.getDb();

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
                        uID = MainActivity.UserID;
                        User user = user = db.userDao().checkUid(uID);
                        System.out.println(user.getUserName() + "---" + user.getPassword());
                        user.setPassword(pw1);
                        db.userDao().insert(user);
                    }).start();
                    Toast.makeText(getActivity(), "success!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Tow passwords are different. Check again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("-----" + uID);
            }
        });
    }
}