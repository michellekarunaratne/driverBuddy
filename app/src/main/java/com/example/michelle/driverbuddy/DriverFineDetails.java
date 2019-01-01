package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DriverFineDetails extends AppCompatActivity {

    public Button pay_button;
    TextView licence,offence,amount;

    public void pay_button()
    {
        pay_button=(Button)findViewById(R.id.pay_button);
        pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity;
                nextActivity = new Intent(DriverFineDetails.this,Payment.class);
                startActivity(nextActivity);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_fine_details);
        //add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pay_button();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }



}
