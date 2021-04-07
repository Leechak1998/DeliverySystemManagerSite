package com.example.deliverysystemmanagersite;

import android.os.Bundle;

import android.view.View;
import com.example.deliverysystemmanagersite.controller.LoginFragment;
import com.example.deliverysystemmanagersite.controller.RegisterFragment;
import com.example.deliverysystemmanagersite.model.myDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LoginFragment LoginFra;
    private RegisterFragment RegisFra;
    public static myDatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        LoginFra = new LoginFragment();
        RegisFra = new RegisterFragment();
        mdb = new myDatabase(getApplicationContext());
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, LoginFra).commitAllowingStateLoss();

        //2、调用对象的set方法，回传接口对象
        LoginFra.setOnButtonClick(new LoginFragment.OnButtonClick() {
            //3、实现接口对象的方法，
            @Override
            public void onClicking(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,RegisFra)
                        .commit();
            }
        });

        RegisFra.setOnButtonClick(new RegisterFragment.OnButtonClick() {
            @Override
            public void onClicking(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,LoginFra)
                        .commit();
            }
        });


    }

}