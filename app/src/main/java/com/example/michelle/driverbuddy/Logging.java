package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Logging extends AppCompatActivity {

    public Button btn_logIn,btn_signin;


    public void driverMenuLogIn()
    {
        btn_logIn=(Button)findViewById(R.id.btn_logIn);

        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(Logging.this,"THis works",Toast.LENGTH_SHORT).show();
                final EditText username = (EditText)findViewById(R.id.editText);
                final EditText password = (EditText)findViewById(R.id.editText2);

                User user=new User(

                        username.getText().toString(),
                        password.getText().toString()
                );

                sendNetworkRequestForType(user);
                /*if(username.getText().toString().trim().equals("insurance") && password.getText().toString().equals("abc")) {
                    Intent nextActivity = new Intent(Logging.this, insurance_profile.class);
                    startActivity(nextActivity);
                }
                else if (username.getText().toString().trim().equals("police") && password.getText().toString().equals("abc")){
                    Intent nextActivity= new Intent(Logging.this,PoliceOfficerMenu.class);
                    startActivity(nextActivity);
                }
                else if (username.getText().toString().trim().equals("driver") && password.getText().toString().equals("abc"))
                {
                    Intent nextActivity1 = new Intent(Logging.this, DriverMenu.class);
                    startActivity(nextActivity1);
                }*/



            }
        });
    }


    public void signup()
    {
        btn_signin=(Button)findViewById(R.id.btn_signUp);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent nextActivity1 = new Intent(Logging.this, DriverRegistration.class);
                    startActivity(nextActivity1);



            }
        });

    }

    public void sendNetworkRequestForType(final User user)
    {
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create());


        Retrofit retrofit=builder.build();

        Api userLogin=retrofit.create(Api.class);
        Call<User> call=userLogin.getProfileType(user);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String type= response.body().getType();
                //Toast.makeText(Logging.this,type,Toast.LENGTH_SHORT).show();
                sendNetworkRequestForAccount(type,user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Logging.this,"Something Went Wrong"+t,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendNetworkRequestForAccount(String type, final User user)
    {
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create());


        Retrofit retrofit=builder.build();

        Api userLogin=retrofit.create(Api.class);

        if(type.equals("Driver"))
        {
            //Toast.makeText(Logging.this,"hello",Toast.LENGTH_SHORT).show();
            Call<Driver> call=userLogin.loginDriver(user);


            call.enqueue(new Callback<Driver>() {
                @Override
                public void onResponse(Call<Driver> call, Response<Driver> response) {

                    Intent driverMenu = new Intent(Logging.this, DriverMenu.class);
                    String name= response.body().getFirstName()+" "+response.body().getLastName();
                    String license= String.valueOf(response.body().getLicense());
                    String email= response.body().getEmail();
                    int mobile= response.body().getMobile();
                    String nic= response.body().getNic();

                    SharedPreferences driverPreferences=getSharedPreferences("driverDetails",MODE_PRIVATE);
                    SharedPreferences.Editor editor= driverPreferences.edit();
                    editor.putString("Name",name);
                    editor.putString("License",license);
                    editor.putString("Email",email);
                    editor.putInt("Mobile",mobile);
                    editor.putString("Nic",nic);
                    editor.commit();
                    startActivity(driverMenu);
                    finish();
                }

                @Override
                public void onFailure(Call<Driver> call, Throwable t) {
                    Toast.makeText(Logging.this,"Something Went Wrong"+t,Toast.LENGTH_SHORT).show();
                }
            });
        }

        else if(type.equals("Police"))
        {
            Call<Police> call=userLogin.loginPolice(user);


            call.enqueue(new Callback<Police>() {
                @Override
                public void onResponse(Call<Police> call, Response<Police> response) {

                    Intent policeMenu = new Intent(Logging.this,PoliceOfficerMenu.class);


                    String name= response.body().getFirstName()+" "+response.body().getLastName();
                    String policeId= String.valueOf(response.body().getPoliceId());
                    String email= response.body().getEmail();
                    int mobile= response.body().getMobile();
                    String nic= response.body().getNic();

                    SharedPreferences policePreferences=getSharedPreferences("policeDetails",MODE_PRIVATE);
                    SharedPreferences.Editor editor= policePreferences.edit();
                    editor.putString("Name",name);
                    editor.putString("PoliceId",policeId);
                    editor.putString("Email",email);
                    editor.putInt("Mobile",mobile);
                    editor.putString("Nic",nic);
                    editor.commit();

                    startActivity(policeMenu);
                    finish();
                }

                @Override
                public void onFailure(Call<Police> call, Throwable t) {
                    Toast.makeText(Logging.this,"Something Went Wrong"+t,Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(type.equals("Insurance"))
        {
            Call<Insurance> call=userLogin.loginInsurance(user);


            call.enqueue(new Callback<Insurance>() {
                @Override
                public void onResponse(Call<Insurance> call, Response<Insurance> response) {

                    Intent insuranceMenu = new Intent(Logging.this, insurance_profile.class);

                    String name= response.body().getFirstName()+" "+response.body().getLastName();
                    String policeId= String.valueOf(response.body().getAgentId());
                    String email= response.body().getEmail();
                    int mobile= response.body().getMobile();
                    String nic= response.body().getNic();

                    SharedPreferences insurancePreferences=getSharedPreferences("insuranceDetails",MODE_PRIVATE);
                    SharedPreferences.Editor editor= insurancePreferences.edit();
                    editor.putString("Name",name);
                    editor.putString("AgentId",policeId);
                    editor.putString("Email",email);
                    editor.putInt("Mobile",mobile);
                    editor.putString("Nic",nic);
                    editor.commit();
                    startActivity(insuranceMenu);
                    finish();
                }

                @Override
                public void onFailure(Call<Insurance> call, Throwable t) {
                    Toast.makeText(Logging.this,"Something Went Wrong"+t,Toast.LENGTH_SHORT).show();
                    Log.d("errorLogin",t.toString());

                }
            });
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        driverMenuLogIn();
        signup();


    }


}
