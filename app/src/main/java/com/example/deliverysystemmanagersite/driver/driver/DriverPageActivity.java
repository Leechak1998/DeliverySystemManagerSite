package com.example.deliverysystemmanagersite.driver.driver;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.deliverysystemmanagersite.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DriverPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_page);

        BottomNavigationView navView = findViewById(R.id.nav_view_driver);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_driver);
        NavigationUI.setupActionBarWithNavController(this, navController);
        NavigationUI.setupWithNavController(navView, navController);
        /*
        *  Navigation.findNavController(root);
            NavHostFragment.findNavController(fra).navigate(R.id.navigation_DriverDetail);
        * */

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController controller = Navigation.findNavController(this,R.id.nav_host_fragment_driver);
        return controller.navigateUp();
    }

}