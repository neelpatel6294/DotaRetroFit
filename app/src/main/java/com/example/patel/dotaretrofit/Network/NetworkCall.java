package com.example.patel.dotaretrofit.Network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.patel.dotaretrofit.Model.Heroes;
import com.example.patel.dotaretrofit.RetroClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall {

    private static MutableLiveData<List<Heroes>> heroList = new MutableLiveData<>();


    public static void fetchData() {
        GetDataService service = RetroClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Heroes>> call = service.getAllData();
        call.enqueue(new Callback<List<Heroes>>() {
            @Override
            public void onResponse(Call<List<Heroes>> call, Response<List<Heroes>> response) {
                List<Heroes> data = response.body();
                heroList.postValue(data);

            }

            @Override
            public void onFailure(Call<List<Heroes>> call, Throwable t) {

            }
        });
    }

    public static LiveData<List<Heroes>> getNetworkData() {
        return heroList;
    }

}
