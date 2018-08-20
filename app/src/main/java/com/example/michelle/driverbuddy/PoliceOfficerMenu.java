package com.example.michelle.driverbuddy;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class PoliceOfficerMenu extends AppCompatActivity {

    private DrawerLayout officer_drawer;
    private ActionBarDrawerToggle ntoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_officer_menu);
        officer_drawer = (DrawerLayout)findViewById(R.id.officer_drawer);
        ntoggle = new ActionBarDrawerToggle(this,officer_drawer,R.string.open, R.string.close);

        officer_drawer.addDrawerListener(ntoggle);
        ntoggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (ntoggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
