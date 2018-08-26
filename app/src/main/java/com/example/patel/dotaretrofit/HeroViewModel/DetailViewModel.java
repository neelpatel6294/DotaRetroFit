package com.example.patel.dotaretrofit.HeroViewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.patel.dotaretrofit.HeroRepository;
import com.example.patel.dotaretrofit.Model.Heroes;

import java.util.List;

public class DetailViewModel extends ViewModel {

    LiveData<List<Heroes>> mData;

    private HeroRepository mRepository;

    public LiveData<List<Heroes>> getFav() {
        return mData;
    }

    public DetailViewModel(int id, Application application) {
        mRepository = new HeroRepository(id,application);
    }

    public void insert(Heroes heroes){
        mRepository.insert(heroes);
    }

}
