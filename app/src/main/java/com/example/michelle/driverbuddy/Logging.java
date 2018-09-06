package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Logging extends AppCompatActivity {

    public Button btn_logIn,btn_signin;

    public void driverMenuLogIn()
    {
        btn_logIn=(Button)findViewById(R.id.btn_logIn);

        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = (EditText)findViewById(R.id.editText);
                EditText password = (EditText)findViewById(R.id.editText2);
                if(username.getText().toString().equals("abc") && password.getText().toString().equals("abc")) {
                    Intent nextActivity = new Intent(Logging.this, insurance_profile.class);
                    startActivity(nextActivity);
                }
                else
                {
                    Intent nextActivity1 = new Intent(Logging.this, DriverMenu.class);
                    startActivity(nextActivity1);
                }


            }
        });
        btn_signin=(Button)findViewById(R.id.btn_signUp);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    Intent nextActivity1 = new Intent(Logging.this, Insurance_registration.class);
                    startActivity(nextActivity1);



            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        driverMenuLogIn();
    }


}
