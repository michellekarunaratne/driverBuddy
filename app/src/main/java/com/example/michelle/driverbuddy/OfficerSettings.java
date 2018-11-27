package com.example.michelle.driverbuddy;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfficerSettings extends AppCompatActivity {

    TextView name,email,mobile;
    Button editProfileData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_settings);
        //add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name=findViewById(R.id.editPoliceProfileName);
        email=findViewById(R.id.editPoliceProfileEmail);
        mobile=findViewById(R.id.editPoliceProfileMobile);

        SharedPreferences preferences = getSharedPreferences("policeDetails", MODE_PRIVATE);
        name.setText(preferences.getString("Name","N/A"));
        email.setText(preferences.getString("Email","N/A"));
        mobile.setText(String.valueOf(preferences.getInt("Mobile", 0)));

        editProfileData=(Button)findViewById(R.id.editPoliceProfileButton);
        editProfileData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences("policeDetails", MODE_PRIVATE);
                String[] nameArray = name.getText().toString().split(" ");
                String nic = preferences.getString("Nic", "N/A");


               Police police = new Police(

                        nic,
                        nameArray[0],
                        nameArray[1],
                        Integer.parseInt(mobile.getText().toString()),
                        email.getText().toString(),
                        preferences.getString("PoliceId","N/A")
                );

                sendNetworkRequestForEdit(police);
            }
        });
    }

    public void sendNetworkRequestForEdit(Police police)
    {
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create());


        Retrofit retrofit=builder.build();

        Api editPoliceProfile=retrofit.create(Api.class);
        Call<Police> call=editPoliceProfile.editPoliceProfile(police);
        call.enqueue(new Callback<Police>() {
            @Override
            public void onResponse(Call<Police> call, Response<Police> response) {
                Toast.makeText(OfficerSettings.this,"Sucessfully Changed User Data",Toast.LENGTH_SHORT).show();
                save();
            }

            @Override
            public void onFailure(Call<Police> call, Throwable t) {
                Toast.makeText(OfficerSettings.this,"Unsucessfull,Cannot Change User Data",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void save()
    {
        Toast.makeText(OfficerSettings.this,"This Works",Toast.LENGTH_SHORT).show();
        SharedPreferences preferences = getSharedPreferences("policeDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Name", name.getText().toString());
        editor.putString("Email", email.getText().toString());
        editor.putInt("Mobile", Integer.parseInt(mobile.getText().toString()));
        editor.commit();
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
