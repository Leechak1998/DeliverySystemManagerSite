package com.example.deliverysystemmanagersite.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deliverysystemmanagersite.MainActivity;
import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.AppDatabase;
import com.example.deliverysystemmanagersite.model.User;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {
    public EditText etUsername;
    public EditText etPassword;
    public EditText etEmail;
    public EditText etPhone;
    public Button btnSub;
    public Button btnCan;
    public TextView hideTv1;
    private View view;
    private AppDatabase db;
    private OnButtonClick onButtonClick;

    private static final int EXIST = 0;
    private static final int AVAILABLE = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if (msg.what == EXIST){
                etEmail.setTextColor(Color.RED);
                hideTv1.setVisibility(View.VISIBLE);
            } else if(msg.what == AVAILABLE){
                etEmail.setTextColor(Color.BLACK);
                hideTv1.setVisibility(View.GONE);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_register, null);

        init();
        Listener();
        return view;
    }

    public void init(){
        etUsername = (EditText)view.findViewById(R.id.etUsername);
        etPassword = (EditText)view.findViewById(R.id.etPassword);
        etEmail = (EditText)view.findViewById(R.id.etEmail);
        etPhone = (EditText)view.findViewById(R.id.etPhone);
        btnSub = (Button)view.findViewById(R.id.btnSub);
        btnCan = (Button)view.findViewById(R.id.btnCancel);
        hideTv1 = (TextView)view.findViewById(R.id.hideTv1);

        db = MainActivity.mdb.getDb();
    }

    public void Listener(){
        //Click button to submit the registration information
        btnSub.setOnClickListener(view -> {
            userRegistration();
            if ("OK".equals(userRegistration())) {
                Toast.makeText(getActivity(), "Registration success", Toast.LENGTH_SHORT).show();
                if (onButtonClick != null) {
                    onButtonClick.onClicking(btnSub);
                }
            } else {
                Toast.makeText(getActivity(), "Please fill in all the information! ", Toast.LENGTH_SHORT).show();
            }

        });

        //Click button to cancel registration
        btnCan.setOnClickListener(view -> {
            if (onButtonClick != null) {
                onButtonClick.onClicking(btnCan);
            }
        });

        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    new Thread(() -> etEmail.setTextColor(Color.BLACK)).start();
                } else {
                    // 此处为失去焦点时的处理内容
                    new Thread(() -> {
                        Message msg;
                        if(db.userDao().checkEmail(etEmail.getText().toString()) != null){
                            //This username already exists
                            msg = new Message();
                            msg.what = EXIST;
                            handler.sendMessage(msg);
                        }else{
                            //This username is available
                            msg = new Message();
                            msg.what = AVAILABLE;
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
            }
        });

    }

    public String userRegistration(){
        if (formCheck() == 0)
            return "False";
        new Thread(() -> {
            //db = MainActivity.mdb.getDb();
            User user = new User();
            user.setUid(setUserUid());
            user.setUserName(etUsername.getText().toString());
            user.setPassword(etPassword.getText().toString());
            user.setEmail(etEmail.getText().toString());
            user.setPhoneNum(etPhone.getText().toString());
            db.userDao().insert(user);
        }).start();
        return "OK";
    }

    public int formCheck(){
        if(etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("") || etEmail.getText().toString().equals("") || etPhone.getText().toString().equals(""))
            return 0;
        else
            return 1;

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
