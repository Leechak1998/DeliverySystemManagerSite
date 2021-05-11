package com.example.deliverysystemmanagersite.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.deliverysystemmanagersite.MainActivity;
import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.controller.activity.HomeActivity;
import com.example.deliverysystemmanagersite.driver.driver.DriverPageFragment;
import com.example.deliverysystemmanagersite.db.AppDatabase;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;
    private RadioButton rBtnManager;
    private RadioButton rBtnDriver;
    private AppDatabase db;
    private View root;
    private OnButtonClick onButtonClick;

    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;

    private static final int NO_USER = 0;
    private static final int INCORRECT_PASSWORD = 1;
    private static final int LOGIN_SUCCESS = 2;

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

    private Handler handlerDriver = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == NO_USER){
                Toast.makeText(getActivity(), "User not exist." , Toast.LENGTH_LONG).show();
            }else if(msg.what == INCORRECT_PASSWORD){
                Toast.makeText(getActivity(), "Incorrect password." , Toast.LENGTH_LONG).show();
            }else if(msg.what == LOGIN_SUCCESS){
                Toast.makeText(getActivity(), "Success." , Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate( R.layout.fragment_login, null);
        init();
        btnListener();
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        etUsername.setText("");
        etPassword.setText("");
    }

    public void init(){
        etUsername = (EditText)root.findViewById(R.id.etUsername);
        etPassword = (EditText)root.findViewById(R.id.etPassword);
        btnLogin = (Button)root.findViewById(R.id.btnLogin);
        btnRegister = (Button)root.findViewById(R.id.btnRegister);
        rBtnDriver = (RadioButton)root.findViewById(R.id.rBtnDriver);
        rBtnManager = (RadioButton)root.findViewById(R.id.rBtnManager);

        db = MainActivity.mdb.getDb();
    }

    public void btnListener(){
        btnRegister.setOnClickListener(view -> {
            if(rBtnManager.isChecked()){
            if (onButtonClick != null) {
                onButtonClick.onClicking(btnRegister);
            }}
        });

        btnLogin.setOnClickListener(view -> {
            if (rBtnManager.isChecked()){
                new Thread(() -> {
                    Message msg;

                    if ("Success".equals(checkUser(etUsername.getText().toString(), etPassword.getText().toString()))){
                        msg = new Message();
                        msg.what = SUCCESS;
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        msg = new Message();
                        msg.what = FAILURE;
                    }
                    handler.sendMessage(msg);
                }).start();
            } else if(rBtnDriver.isChecked()) {
                new Thread(()->{
                    HttpConnectionUtil htc = new HttpConnectionUtil();
                    String userName = etUsername.getText().toString();
                    String pwd = etPassword.getText().toString();
                    String s = htc.doGet("http://10.0.2.2:8339/loginDriver?email=" + userName + "&password=" + pwd);
                    Message m = new Message();
                    System.out.println(s);
                    if(s.equals("0")){
                        m.what = NO_USER;
                    }else if(s.equals("1")){
                        m.what = INCORRECT_PASSWORD;
                    }else{
                        m.what = LOGIN_SUCCESS;
                        Intent intent = new Intent(getActivity(), DriverPageFragment.class);
                        startActivity(intent);
                    }
                    handlerDriver.sendMessage(m);
                }).start();
            } else{
                Toast.makeText(getActivity(), "Please select a role to login.", Toast.LENGTH_LONG).show();
            }
            //Validate username and password

        });



    }

//    public OnButtonClick getOnButtonClick() {
//        return onButtonClick;
//    }

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