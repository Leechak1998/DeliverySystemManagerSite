package com.example.deliverysystemmanagersite.controller.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_workList, R.id.navigation_add, R.id.navigation_mine)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

}