package com.example.patel.dotaretrofit.HeroViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.patel.dotaretrofit.HeroRepository;
import com.example.patel.dotaretrofit.Model.Heroes;

import java.util.List;

public class HeroViewModel extends AndroidViewModel {

    private LiveData<List<Heroes>> mData;
    private LiveData<List<Heroes>> mDataFav;

    private HeroRepository mRepository;

    public HeroViewModel(@NonNull Application application) {
        super(application);
        mRepository = new HeroRepository(application);
    }

    public LiveData<List<Heroes>> mLiveData() {
        mData = mRepository.mLiveData();
        return mData;
    }

    public LiveData<List<Heroes>> mLiveDataFav() {
        if (mDataFav == null) {
            mDataFav = new MutableLiveData<>();
        }
        mDataFav = mRepository.getLiveFavData();
        return mDataFav;
    }

    public void getFavData() {
        mRepository.getFavData();

    }

    public void deleteHero(int id){
        mRepository.deleteHero(id);
    }
}
