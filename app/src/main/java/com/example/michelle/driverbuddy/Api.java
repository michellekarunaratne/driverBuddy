package com.example.michelle.driverbuddy;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
   String BASE_URL= "http://10.22.165.0:3000/";

    @POST("driverRegister")
    Call<Driver> createAccount(@Body Driver driver);

    @POST("userRegister")
    Call<User> createProfile(@Body User user);

    @POST("userAccountType")
    Call<User> getProfileType(@Body User user);

    @POST("userLogin")
    Call<Driver> loginDriver(@Body User user);

    @POST("userLogin")
    Call<Police> loginPolice(@Body User user);

    @POST("userLogin")
    Call<Insurance> loginInsurance(@Body User user);

    @POST("driverEdit")
    Call<Driver> editDriverProfile(@Body Driver driver);

    @POST("policeEdit")
    Call<Police> editPoliceProfile(@Body Police police);

    @POST("insuranceEdit")
    Call<Insurance> editInsuranceProfile(@Body Insurance insurance);

    @POST("createFineTicket")
    Call<FineTicket> createFineTicket(@ Body FineTicket fineTicket);

    @GET("checkLicense")
    Call<Driver>checkLicense(@Query("license") String licenseNumber);

    @GET("getTicketDriver")
    Call<ArrayList<FineTicket>> getFineTicketDriver(@Query("nic") String nic);

    @GET("getTicketPolice")
    Call<ArrayList<FineTicket>> getFineTicketPolice(@Query("policeId") String policeId);

    @GET("getfinedetails")
    Call<ArrayList<FineTicket>> getFinedetails(@Query("license") String policeId);

    @GET("viewRecentFineTicket")
    Call<FineTicket> viewRecentUnpaidFineTicket(@Query("nic") String nic);
}
