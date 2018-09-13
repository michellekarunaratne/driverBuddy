package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class RespondDistress extends AppCompatActivity {


    public Button button_track_distress_driver;

    public void track_distress_button()
    {
        button_track_distress_driver=(Button)findViewById(R.id.button_track_distress_driver);
        button_track_distress_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity;
                nextActivity = new Intent(RespondDistress.this,TrackDistressDriver.class);
                startActivity(nextActivity);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respond_distress);
        //add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        track_distress_button();
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
