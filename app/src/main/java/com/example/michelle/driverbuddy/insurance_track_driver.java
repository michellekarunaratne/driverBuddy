package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class insurance_track_driver extends AppCompatActivity {

    public Button but5;

   /*public void init()
    {
        but5=(Button)findViewById(R.id.button5);
        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b= new Intent(insurance_track_driver.this,insurance_popwindow.class);
                startActivity(b);
            }
        });
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_track_driver);
      //  init();

    }
}
