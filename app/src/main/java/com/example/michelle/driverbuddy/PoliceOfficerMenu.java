package com.example.michelle.driverbuddy;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
    public Button button_write_fine, button_check_license, button_distress_respond;
    NavigationView navigationView;
    android.support.v4.app.FragmentTransaction fragmentTransaction;

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
        //check_license_button();
        distress_respond_button();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.nav_fine_history:
                        Intent fine = new Intent(PoliceOfficerMenu.this,FineHistoryOfficer.class);
                        startActivity(fine);
                        //item.setChecked(true);
                        officer_drawer.closeDrawers();
                        break;

                    case R.id.nav_edit_fine:
                        Intent edit = new Intent(PoliceOfficerMenu.this,EditFineOfficer.class);
                        startActivity(edit);
                        //item.setChecked(true);
                        officer_drawer.closeDrawers();
                        break;

                    case R.id.nav_settings:
                        Intent settings = new  Intent(PoliceOfficerMenu.this,OfficerSettings.class);
                        startActivity(settings);
                        //item.setChecked(true);
                        officer_drawer.closeDrawers();
                        break;

                    case R.id.nav_log_out:
                        Intent log_out = new  Intent(PoliceOfficerMenu.this,Logging.class);
                        startActivity(log_out);
                        //item.setChecked(true);
                        officer_drawer.closeDrawers();
                        finish();
                        break;


                }

                return false;
            }
        });
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

    /*public void check_license_button()
    {
        //button_check_license=(Button)findViewById(R.id.button_check_license);
        button_check_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity;
                nextActivity = new Intent(PoliceOfficerMenu.this,CheckLicense.class);
                startActivity(nextActivity);
            }
        });
    }*/

    public void distress_respond_button()
    {
        button_distress_respond=(Button)findViewById(R.id.button_distress_respond);
        button_distress_respond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity;
                nextActivity = new Intent(PoliceOfficerMenu.this,RespondDistress.class);
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
