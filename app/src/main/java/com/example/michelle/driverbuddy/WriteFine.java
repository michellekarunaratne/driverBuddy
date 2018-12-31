package com.example.michelle.driverbuddy;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WriteFine extends AppCompatActivity {

    Spinner fineNameSpinner;
    String fineNames[]={"crossing double lane","using mobile while driving","driving without helmet","exceeding speed limit"};
    ArrayAdapter<String> adapter;
    String fineName="";
    Button issueFineButton;
    int min,max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_fine);
        //add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fineNameSpinner =(Spinner) findViewById(R.id.fineNameSpinner);
        issueFineButton =(Button)findViewById(R.id.issueFineButton);
        final EditText nic=(EditText)findViewById(R.id.fineDriverNic);
        final EditText vehicleNumber=(EditText) findViewById(R.id.fineDriverVehicleNumber);
        final EditText amount=(EditText) findViewById(R.id.fineDriverAmount);


        adapter=new ArrayAdapter<String>(WriteFine.this,
                android.R.layout.simple_list_item_1,fineNames);


        fineNameSpinner.setAdapter(adapter);

        fineNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        fineName="crossing double lane";
                        min=500;
                        max=1000;
                        break;

                    case 1:
                        fineName="using mobile while driving";
                        break;

                    case 2:
                        fineName="driving without helmet";
                        break;

                    case 3:
                        fineName="exceeding speed limit";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        issueFineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText nic=(EditText)findViewById(R.id.fineDriverNic);
                final EditText vehicleNumber=(EditText) findViewById(R.id.fineDriverVehicleNumber);
                final EditText amount=(EditText) findViewById(R.id.fineDriverAmount);

                boolean next=true;

                String nicText=nic.getText().toString().toLowerCase();
                if(amount.getText().length()==0)
                {
                    amount.setError("Amount Should Not Be Empty");
                    next=false;
                }


                if(nicText.length()!=10||!nicText.contains("v"))
                {
                    nic.setError("Invalid NIC");
                    next=false;
                }
                if(next)
                {
                    int amountInt=Integer.parseInt(amount.getText().toString());
                    if(amountInt>=min && amountInt<=max)
                    {
                        SharedPreferences preferences = getSharedPreferences("policeDetails", MODE_PRIVATE);
                        String policeId = preferences.getString("PoliceId", "N/A").trim();


                        FineTicket fineTicket = new FineTicket(
                                vehicleNumber.getText().toString().trim(),
                                Integer.parseInt(amount.getText().toString().toString().trim()),
                                nic.getText().toString().trim(),
                                policeId,
                                fineName
                        );

                        sendNetworkRequest(fineTicket);

                    }
                    else {
                        amount.setError("Fine Amount Is Not In The Allocated Range");
                    }
                }


            }
        });

    }

    private void sendNetworkRequest(FineTicket fineTicket)
    {
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create());


        Retrofit retrofit=builder.build();

        Api writeFine=retrofit.create(Api.class);
        SharedPreferences preferences = getSharedPreferences("policeDetails", MODE_PRIVATE);
        String token=preferences.getString("Token","Null");
        Call<FineTicket>call=writeFine.createFineTicket(token,fineTicket);
        call.enqueue(new Callback<FineTicket>() {
            @Override
            public void onResponse(Call<FineTicket> call, Response<FineTicket> response) {
                Toast.makeText(WriteFine.this,"Sucessfully Issued The Fine Ticket",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<FineTicket> call, Throwable t) {
                Toast.makeText(WriteFine.this,"Unsucessfull,Something Went Wrong"+t,Toast.LENGTH_SHORT).show();
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
