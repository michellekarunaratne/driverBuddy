package com.example.michelle.driverbuddy;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL= "http://localhost:3000/";

    @POST("Registration")
    Call<User>creatAccount(@Body User user);
}