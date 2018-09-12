package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

public class DriverMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_menu);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView=(NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if( actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            Intent editProfileIntent=new Intent(DriverMenu.this,DriverProfileEditActivity.class);
            startActivity(editProfileIntent);
        }

        if (id == R.id.nav_logout) {
            Intent editProfileIntent=new Intent(DriverMenu.this,Logging.class);
            startActivity(editProfileIntent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}

