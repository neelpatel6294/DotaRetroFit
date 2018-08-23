package com.example.patel.dotaretrofit.HeroViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.patel.dotaretrofit.HeroRepository;
import com.example.patel.dotaretrofit.Model.Heroes;

import java.util.List;

public class HeroViewModel extends AndroidViewModel {

    private LiveData<List<Heroes>> mData;
    private HeroRepository mRepository;

    public HeroViewModel(@NonNull Application application) {
        super(application);
        mRepository = new HeroRepository(application);
        mData = mRepository.mLiveData();
    }

    public LiveData<List<Heroes>> mLiveData() {
        return mData;
    }
}
