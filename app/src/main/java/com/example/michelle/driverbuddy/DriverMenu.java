package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DriverMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    public Button pay_fine,report_accident,distress_button;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_menu);
        report_accident_button();
        distress_button();
        pay_fine_button();

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_histroy:
                Intent newActivity= new Intent(DriverMenu.this,FineHistory.class);
                startActivity(newActivity);
                break;

            case R.id.nav_setting:
                Intent driverEditActivity= new Intent(DriverMenu.this,DriverProfileEditActivity.class);
                startActivity(driverEditActivity);
                break;

            case R.id.nav_logout:
                Intent logOutActivity=new Intent(DriverMenu.this,Logging.class);
                startActivity(logOutActivity);
                finish();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void report_accident_button()
    {
        report_accident=(Button)findViewById(R.id.report_accident);
        report_accident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity;
                nextActivity = new Intent(DriverMenu.this,DriverReportAccident.class);
                startActivity(nextActivity);
            }
        });
    }

    public void distress_button()
    {
        distress_button=(Button)findViewById(R.id.distress_button);
        distress_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity;
                nextActivity = new Intent(DriverMenu.this,DistressButton.class);
                startActivity(nextActivity);
            }
        });
    }

    public void pay_fine_button()
    {
        pay_fine=(Button)findViewById(R.id.pay_fine);
        pay_fine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity;
                nextActivity = new Intent(DriverMenu.this,DriverFineDetails.class);
                startActivity(nextActivity);
            }
        });
    }


}
