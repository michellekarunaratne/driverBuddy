package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PoliceOfficerMenu extends AppCompatActivity {

    private DrawerLayout officer_drawer;
    private ActionBarDrawerToggle ntoggle;
    public Button button_write_fine, button_check_license;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_officer_menu);
        officer_drawer = (DrawerLayout)findViewById(R.id.officer_drawer);
        ntoggle = new ActionBarDrawerToggle(this,officer_drawer,R.string.open, R.string.close);

        officer_drawer.addDrawerListener(ntoggle);
        ntoggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        write_fine_button();
        check_license_button();
    }


    public void write_fine_button()
    {
        button_write_fine=(Button)findViewById(R.id.button_write_fine);
        button_write_fine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity;
                nextActivity = new Intent(PoliceOfficerMenu.this,WriteFine.class);
                startActivity(nextActivity);
            }
        });
    }

    public void check_license_button()
    {
        button_check_license=(Button)findViewById(R.id.button_check_license);
        button_check_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity;
                nextActivity = new Intent(PoliceOfficerMenu.this,CheckLicense.class);
                startActivity(nextActivity);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (ntoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
