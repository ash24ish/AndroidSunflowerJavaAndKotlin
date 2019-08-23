

package com.google.samples.apps.sunflower;

import android.os.Bundle;

import com.google.samples.apps.sunflower.databinding.ActivityGardenBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class GardenActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityGardenBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_garden);
        this.drawerLayout = binding.drawerLayout;
        this.navController = Navigation.findNavController(this, R.id.garden_nav_fragment);
        this.appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build();

        // Set up ActionBar
        setSupportActionBar(binding.toolbar);
        NavigationUI.setupActionBarWithNavController(this, this.navController, this.appBarConfiguration);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up navigation menu
        NavigationUI.setupWithNavController(binding.navigationView, this.navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
