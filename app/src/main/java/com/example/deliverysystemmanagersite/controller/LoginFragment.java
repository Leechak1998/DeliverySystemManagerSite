package com.example.deliverysystemmanagersite.controller;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    public EditText etUsername;
    public EditText etPassword;
    public Button btnLogin;
    public Button btnRegister;
    public AppDatabase db;
    private View view;
    private OnButtonClick onButtonClick;
    private String toastFlag = "default";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_login, null);
        init();
        btnListener();
        return view;
    }

    public void init(){
        etUsername = (EditText)view.findViewById(R.id.etUsername);
        etPassword = (EditText)view.findViewById(R.id.etPassword);
        btnLogin = (Button)view.findViewById(R.id.btnLogin);
        btnRegister = (Button)view.findViewById(R.id.btnRegister);


    }

    public void btnListener(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onButtonClick != null) {
                    onButtonClick.onClicking(btnRegister);
                }
            }
        });

        btnLogin.setOnClickListener(view -> {
            db = MainActivity.mdb.getDb();
            //Validate username and password
            new Thread(() -> toastFlag = checkUser(etUsername.getText().toString(), etPassword.getText().toString())).start();
            Toast.makeText(getActivity(),toastFlag,Toast.LENGTH_LONG).show();
        });
    }

    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    public interface OnButtonClick{
        public void onClicking(View view);
    }

    public String checkUser(String userN, String userP){
        if(db.userDao().checkUser(userN, userP) != null)
            return "Success";
        else
            return "Failure";

    }

}