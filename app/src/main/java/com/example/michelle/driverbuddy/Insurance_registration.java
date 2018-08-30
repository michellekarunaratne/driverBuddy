package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Insurance_registration extends AppCompatActivity {

    public Button but1;

    public void init()
    {      but1 = (Button)findViewById(R.id.button3);
           but1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent b= new Intent(Insurance_registration.this,Logging.class);
                   startActivity(b);
               }
           });






    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_registration);
        init();
    }
}
