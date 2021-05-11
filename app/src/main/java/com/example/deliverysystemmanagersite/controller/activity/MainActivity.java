package com.example.deliverysystemmanagersite.controller.activity;

import android.content.Context;
import android.os.Bundle;

import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.controller.fragment.LoginFragment;
import com.example.deliverysystemmanagersite.controller.fragment.RegisterFragment;
import com.example.deliverysystemmanagersite.db.myDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static int UserID;
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
        //3、实现接口对象的方法，
        LoginFra.setOnButtonClick(view -> getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,RegisFra)
                .commit());

        RegisFra.setOnButtonClick(view -> getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,LoginFra)
                .commit());



    }

    //点击空白处软键盘自动收回
    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN) {//把操作放在用户点击的时候
            View v = getCurrentFocus(); //得到当前页面的焦点,ps:有输入框的页面焦点一般会被输入框占据
            if (isShouldHideKeyboard(v, me))
            { //判断用户点击的是否是输入框以外的区域
                hideKeyboard(v.getWindowToken());
                //收起键盘
            } } return super.dispatchTouchEvent(me); }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) { if (v != null && (v instanceof EditText)) {
        //判断得到的焦点控件是否包含EditText
        int[] l = {0, 0}; v.getLocationInWindow(l);
        //得到输入框在屏幕中上下左右的位置
        int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
        if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
            // 点击位置如果是EditText的区域，忽略它，不收起键盘。
            return false; } else { return true; } }
        // 如果焦点不是EditText则忽略
        return false; }

    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        } }
//点击空白处软键盘自动收回


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}