package com.example.deliverysystemmanagersite.controller.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.controller.fragment.PackageDetailsFragment;
import com.example.deliverysystemmanagersite.controller.fragment.PackageFragment;
import com.example.deliverysystemmanagersite.controller.fragment.RegisterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeActivity extends AppCompatActivity {
    private PackageFragment packageFragment;
    private PackageDetailsFragment packageDetailsFragment;
    private NavController navController;
    private HomeActivity act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        act = this;
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_package, R.id.navigation_driver, R.id.navigation_vendor, R.id.navigation_site, R.id.navigation_packageDetails)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


}