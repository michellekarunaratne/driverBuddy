package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DriverFineDetails extends AppCompatActivity {

    public Button pay_button;
    TextView nic_1,vehicle_no,offence,amount;

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

        nic_1 = findViewById(R.id.driver_nic);
        vehicle_no = findViewById(R.id.driver_vehicleno);
        offence = findViewById(R.id.driver_offence);
        amount = findViewById(R.id.driver_amount);


    }

    public void sendNetworkRequestForGetRecentTicket( )
    {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit=builder.build();

        Api fine_details=retrofit.create(Api.class);

        SharedPreferences preferences = getSharedPreferences("driverDetails", MODE_PRIVATE);
        String nic=preferences.getString("Nic","Null");
        Call<FineTicket> call = fine_details.viewRecentUnpaidFineTicket(nic);
        call.enqueue(new Callback<FineTicket>() {
            @Override
            public void onResponse(Call<FineTicket> call, Response<FineTicket> response) {
            nic_1.setText(response.body().getDriver()[0].getNic());
            vehicle_no.setText(response.body().getVehicleNumber());
            offence.setText(response.body().getFine()[0].getDescription());
            amount.setText(response.body().getAmount());
            }

            @Override
            public void onFailure(Call<FineTicket> call, Throwable t) {
                Toast.makeText(DriverFineDetails.this,"No un-paid fines",Toast.LENGTH_SHORT).show();

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
