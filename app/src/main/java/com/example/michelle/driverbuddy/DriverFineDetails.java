package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DriverFineDetails extends AppCompatActivity {

    public Button to_pay_button;
    EditText nic_1,vehicle_no,offence,amount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_fine_details);
        //add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        to_pay_button();

        /*button = findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNetworkRequestForGetRecentTicket();
            }
        });*/

        nic_1 = (EditText)findViewById(R.id.driver_nic);
        vehicle_no =(EditText)findViewById(R.id.driver_vehicleno);
        offence =(EditText)findViewById(R.id.driver_offence_1);
        amount = (EditText)findViewById(R.id.driver_amount_fine);

        sendNetworkRequestForGetRecentTicket();

    }

    public void sendNetworkRequestForGetRecentTicket() {
        Retrofit.Builder builder = new Retrofit.Builder()
                //.baseUrl("http://10.0.2.2:3000/")
                .baseUrl("http://192.168.42.107:3000/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        Api fine_details = retrofit.create(Api.class);

        SharedPreferences preferences = getSharedPreferences("driverDetails", MODE_PRIVATE);
        String nic=preferences.getString("Nic","Null");
        Call<FineTicket> call = fine_details.viewRecentUnpaidFineTicket("957591051v");
        call.enqueue(new Callback<FineTicket>() {

            @Override
            public void onResponse(Call<FineTicket> call, Response<FineTicket> response) {
                nic_1.setText(response.body().getDriver()[0].getNic());
                vehicle_no.setText(response.body().getVehicleNumber());
                offence.setText(response.body().getFine()[0].getDescription());
                amount.setText(String.valueOf(response.body().getAmount()));


            }

            @Override
            public void onFailure(Call<FineTicket> call, Throwable t) {
                Toast.makeText(DriverFineDetails.this,"No un-paid fines",Toast.LENGTH_LONG).show();
                nic_1.setError(String.valueOf(t));
            }
        });

    }

    public void to_pay_button()
    {
        to_pay_button=(Button)findViewById(R.id.to_pay_button);
        to_pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity;
                nextActivity = new Intent(DriverFineDetails.this,Payment.class);


                /*String fName_temp = FineTicket.getDriver()[0].getFirstName();
                String lName_temp   = FineTicket.getDriver()[0].getLastName();
                String email_temp   = FineTicket.getDriver()[0].getEmail();
                String mobile_temp = (String.valueOf(FineTicket.getDriver()[0].getMobile()));
                String offence_temp = FineTicket.getFine()[0].getName();
                String amount_temp = (String.valueOf(FineTicket.getFine()[0].getAmount()));

                nextActivity.putExtra("fname", fName_temp);
                nextActivity.putExtra("lname", lName_temp);
                nextActivity.putExtra("email", email_temp);
                nextActivity.putExtra("mobile", mobile_temp);
                nextActivity.putExtra("offence", offence_temp);
                nextActivity.putExtra("amount", amount_temp);*/

                startActivity(nextActivity);

            }
        });
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
