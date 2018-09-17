package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_registration extends AppCompatActivity {

    public Button but1;

    public void init()
    {      but1 = (Button) findViewById(R.id.userRegistrationButton);
           but1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent settingUpProfile= new Intent(User_registration.this,SettingUpProfileActivity.class);
                   startActivity(settingUpProfile);
               }
           });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        init();
    }
}
