package com.example.deliverysystemmanagersite.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deliverysystemmanagersite.MainActivity;
import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.AppDatabase;
import com.example.deliverysystemmanagersite.model.User;

import java.util.Random;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {
    public EditText etUsername;
    public EditText etPassword;
    public EditText etEmail;
    public EditText etPhone;
    public Button btnSub;
    public Button btnCan;
    private View view;
    private AppDatabase db;
    private OnButtonClick onButtonClick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_register, null);

        init();
        btnListner();
        return view;
    }

    public void init(){
        etUsername = (EditText)view.findViewById(R.id.etUsername);
        etPassword = (EditText)view.findViewById(R.id.etPassword);
        etEmail = (EditText)view.findViewById(R.id.etEmail);
        etPhone = (EditText)view.findViewById(R.id.etPhone);
        btnSub = (Button)view.findViewById(R.id.btnSub);
        btnCan = (Button)view.findViewById(R.id.btnCancel);
    }

    public void btnListner(){
        //Click button to submit the registration information
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegistration();
                if ("OK".equals(userRegistration())){
                    Toast.makeText(getActivity(),"Registration success",Toast.LENGTH_SHORT).show();
                    if (onButtonClick != null){
                        onButtonClick.onClicking(btnSub);
                    }
                }

            }
        });

        //Click button to cancel registration
        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), DataActivity.class);
//                startActivity(intent);
            }
        });
    }

    public String userRegistration(){
        new Thread(() -> {
            db = MainActivity.mdb.getDb();
            User user = new User();
            user.setUid(setUserUid());
            user.setUserName(etUsername.getText().toString());
            user.setPassword(etPassword.getText().toString());
            user.setEmail(etEmail.getText().toString());
            user.setPhoneNum(etPhone.getText().toString());
            db.userDao().insert(user);
            //System.out.println("11111111");
        }).start();
        //System.out.println("222222");
        return "OK";
    }

    public int setUserUid(){
        int uid = 0;
        while(true){
            if(db.userDao().checkUid(uid) != null)
                uid++;
             else
                break;

        }
        System.out.println("-----The latest uid is :" + uid);
        return uid;
    }

    public interface OnButtonClick{
        public void onClicking(View view);
    }

    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

}
