package com.example.michelle.driverbuddy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class insurance_profile extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView name,agentId,email;

    public Button but2,but3;
    public TextView edit;

    public void init()
    /*{
        edit = (TextView) findViewById(R.id.textView33);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a =new Intent(insurance_profile.this,insurance_edit_profile.class);
                startActivity(a);

            }
        });*/
    {
        but3 = (Button)findViewById(R.id.button3);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c=new Intent(insurance_profile.this,insurance_track_driver.class);
                startActivity(c);
            }
        });




    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_profile);

        name=findViewById(R.id.insuranceProfileName);
        agentId=findViewById(R.id.insuranceProfileAgentId);
        email=findViewById(R.id.insuranceProfileEmail);

        SharedPreferences preferences = getSharedPreferences("insuranceDetails",MODE_PRIVATE);
        name.setText(preferences.getString("Name","N/A"));
        email.setText(preferences.getString("Email","N/A"));
        agentId.setText(preferences.getString("AgentId","N/A"));

        init();

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout_1);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.acc_logout)
                {
                    final Intent c=new Intent(insurance_profile.this,Logging.class);
                    startActivity(c);
                }
                else if(id==R.id.acc_edit)
                {
                    Intent e=new Intent(insurance_profile.this,insurance_edit_profile.class);
                    startActivity(e);
                }
                else if(id==R.id.acc_histroy)
                {
                    Intent e=new Intent(insurance_profile.this,Insurance_accident_report.class);
                    startActivity(e);
                }



                return true;
            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
