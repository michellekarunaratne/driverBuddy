package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class User_registration extends AppCompatActivity {

    public Button but1;
    public Button createAccountButton;

    public void init()
    {
            but1 = (Button) findViewById(R.id.userRegistrationButton);

            final EditText firstName = (EditText) findViewById(R.id.registrationFirstName);
            final EditText lastName = (EditText) findViewById(R.id.registrationLastName);
            final EditText email = (EditText) findViewById(R.id.registrationEmail);
            final EditText nic = (EditText) findViewById(R.id.registrationNic);
            final EditText license = (EditText) findViewById(R.id.registrationLicense);
            final EditText mobile = (EditText) findViewById(R.id.registrationMobile);

            but1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   User user=new User(

                           firstName.getText().toString(),
                           lastName.getText().toString(),
                           email.getText().toString(),
                           nic.getText().toString(),
                           Integer.parseInt(license.getText().toString()),
                           Integer.parseInt(mobile.getText().toString()));



                   sendNetworkRequest(user);
               }
           });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        init();
    }

    public void sendNetworkRequest(User user)
    {
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create());


        Retrofit retrofit=builder.build();

        Api userRegistration=retrofit.create(Api.class);
        Call<User> call=userRegistration.createAccount(user);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                EditText userId=(EditText) findViewById(R.id.registrationNic);
                Toast.makeText(User_registration.this,"Yeah User nic"+response.body().getNic(),Toast.LENGTH_SHORT).show();
                Intent settingUpProfile= new Intent(User_registration.this,SettingUpProfileActivity.class);
                settingUpProfile.putExtra("USER ID",userId.getText().toString());
                startActivity(settingUpProfile);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(User_registration.this,"Something Went Wrong"+t,Toast.LENGTH_SHORT).show();
                Intent logging= new Intent(User_registration.this,Logging.class);
                startActivity(logging);
            }
        });
    }



}


