package com.example.patel.dotaretrofit;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.patel.dotaretrofit.Model.Heroes;
import com.example.patel.dotaretrofit.Network.NetworkCall;

import java.util.List;

public class HeroRepository {

    private LiveData<List<Heroes>> mAllWords;


    public HeroRepository(Application application) {

        NetworkCall.fetchData();
    }

    public LiveData<List<Heroes>> mLiveData() {
        mAllWords = NetworkCall.getNetworkData();
        return mAllWords;
    }
}
