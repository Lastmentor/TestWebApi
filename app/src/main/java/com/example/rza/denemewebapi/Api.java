package com.example.rza.denemewebapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "http://githubwebapi.azurewebsites.net/api/";

    @GET("students")
    Call<List<Students>> getStudents();

    @POST("students")
    Call<Students> createStudents(@Body Students students);

}
