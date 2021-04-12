package com.example.deliverysystemmanagersite.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deliverysystemmanagersite.MainActivity;
import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.controller.activity.HomeActivity;
import com.example.deliverysystemmanagersite.db.AppDatabase;

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

    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if (msg.what == SUCCESS){
                Toast.makeText(getActivity(),"Success",Toast.LENGTH_LONG).show();
            } else if(msg.what == FAILURE){
                Toast.makeText(getActivity(),"Invalid email or password. Check again!",Toast.LENGTH_LONG).show();
            }
        }
    };

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

        db = MainActivity.mdb.getDb();
    }

    public void btnListener(){
        btnRegister.setOnClickListener(view -> {
            if (onButtonClick != null) {
                onButtonClick.onClicking(btnRegister);
            }
        });

        btnLogin.setOnClickListener(view -> {
            //Validate username and password
            new Thread(() -> {
                Message msg;
                if ("Success".equals(checkUser(etUsername.getText().toString(), etPassword.getText().toString()))){
                    msg = new Message();
                    msg.what = SUCCESS;
                    handler.sendMessage(msg);
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    msg = new Message();
                    msg.what = FAILURE;
                    handler.sendMessage(msg);
                }

            }).start();
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