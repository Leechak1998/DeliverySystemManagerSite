package com.example.deliverysystemmanagersite.driver.driver;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deliverysystemmanagersite.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DriverPageFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_driver_page);

        BottomNavigationView navView = findViewById(R.id.nav_view_driver);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_driver);
        NavigationUI.setupActionBarWithNavController(this, navController);
        NavigationUI.setupWithNavController(navView, navController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController controller = Navigation.findNavController(this,R.id.nav_host_fragment_driver);
        return controller.navigateUp();
    }

}