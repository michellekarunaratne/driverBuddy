package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SettingUpProfileActivity extends AppCompatActivity {

    public Button btn_done;
    TextView userId;
    EditText password;
    String type="Driver";
    EditText confirmpassword;

    public void SignOut()
    {
        btn_done=(Button) findViewById(R.id.settingUpDone_btn);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProfile();
            }
        });



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_up_profile);
        SignOut();
        userId=(TextView) findViewById(R.id.settingUpProfileUserId);
        userId.setText(getIntent().getStringExtra("USER ID"));




    }

    public void createProfile()
    {
        password=(EditText) findViewById(R.id.settingUpProfilePassword);
        confirmpassword=(EditText) findViewById(R.id.settingUpProfileConfirmPassword);
        String passW=password.getText().toString();
        String confirmP=confirmpassword.getText().toString();

        if(!passW.equals(confirmP))
            {
                password.setError("Password Doesn't Match");
                confirmpassword.setError(("Password Doesn't Match "));
            }
        else
            {
                password = (EditText) findViewById(R.id.settingUpProfilePassword);


                User user = new User(

                        userId.getText().toString(),
                        password.getText().toString(),
                        type

                );

                sendNetworkRequest(user);
            }

    }

    public void sendNetworkRequest(User user)
    {

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create());


        Retrofit retrofit=builder.build();

        Api userRegistration=retrofit.create(Api.class);
        Call<User> call=userRegistration.createProfile(user);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(SettingUpProfileActivity.this,"Yeah User nic"+response.body().getUserId(),Toast.LENGTH_SHORT).show();
                Intent done= new Intent(SettingUpProfileActivity.this, Logging.class);
                startActivity(done);
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SettingUpProfileActivity.this,"Something Went Wrong"+t,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
