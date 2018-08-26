package com.example.patel.dotaretrofit.HeroViewModel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class DetailViewModelFactory extends ViewModelProvider.NewInstanceFactory{


        private final int heroId;
        Application application;

    public DetailViewModelFactory(int heroId, Application application) {
        this.heroId = heroId;
        application = application;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DetailViewModel(heroId,application);
    }
}
