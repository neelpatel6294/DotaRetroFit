package com.example.patel.dotaretrofit.Network;

import com.example.patel.dotaretrofit.Model.Heroes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {


    @GET("/api/heroStats")
    Call<List<Heroes>> getAllData();
}
