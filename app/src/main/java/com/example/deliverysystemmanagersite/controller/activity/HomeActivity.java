package com.example.deliverysystemmanagersite.controller.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.controller.fragment.DriverFragment;
import com.example.deliverysystemmanagersite.controller.fragment.PackageDetailsFragment;
import com.example.deliverysystemmanagersite.controller.fragment.PackageFragment;
import com.example.deliverysystemmanagersite.controller.fragment.RegisterFragment;
import com.example.deliverysystemmanagersite.controller.fragment.SiteFragment;
import com.example.deliverysystemmanagersite.controller.fragment.VendorFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView navView = findViewById(R.id.nav_view);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);

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
    public boolean onSupportNavigateUp() {
        NavController controller = Navigation.findNavController(this,R.id.nav_host_fragment);
        return controller.navigateUp();
    }

}