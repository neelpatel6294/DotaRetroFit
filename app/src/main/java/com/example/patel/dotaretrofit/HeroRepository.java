package com.example.patel.dotaretrofit;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.patel.dotaretrofit.Database.AppDatabase;
import com.example.patel.dotaretrofit.Database.HeroesDao;
import com.example.patel.dotaretrofit.Model.Heroes;
import com.example.patel.dotaretrofit.Network.NetworkCall;

import java.util.List;

public class HeroRepository {

    private LiveData<List<Heroes>> mAllWords;
    LiveData<List<Heroes>> mFav;
    private int heroId;
    HeroesDao mHeroesDao;
    LiveData<List<Heroes>> mdata;

    public HeroRepository(Application application) {

        AppDatabase mDb = AppDatabase.getDatabase(application);
        mHeroesDao = mDb.heroesDao();
        NetworkCall.fetchData();
    }

    public HeroRepository(int heroId,Application application) {

        AppDatabase mDb = AppDatabase.getDatabase(application);
        mHeroesDao = mDb.heroesDao();
        this.heroId = heroId;
        NetworkCall.fetchData();
    }

    public LiveData<List<Heroes>> mLiveData() {
        mAllWords = NetworkCall.getNetworkData();
        return mAllWords;
    }

    public void getFavData() {
        mFav = mHeroesDao.getAllFav();
    }


    public LiveData<List<Heroes>> getLiveFavData() {
        return mFav;
    }

    public void insert(Heroes heroes){
        new insertAysncTask(mHeroesDao).execute(heroes);
    }

    @SuppressLint("StaticFieldLeak")
    private class insertAysncTask extends AsyncTask<Heroes, Void, Void> {

        private HeroesDao mAysncDao;

        private insertAysncTask(HeroesDao mHeroesDao) {

            mAysncDao = mHeroesDao;
        }

        @Override
        protected Void doInBackground(Heroes... heroes) {
            mAysncDao.insert(heroes[0]);
            return null;
        }
    }

    public void deleteHero(int id){
        new deleteAysncTask(mHeroesDao).execute(id);
    }

    @SuppressLint("StaticFieldLeak")
    private class deleteAysncTask extends AsyncTask<Integer, Void, Void> {

        private HeroesDao mAysncDao;

        private deleteAysncTask(HeroesDao mHeroesDao) {

            mAysncDao = mHeroesDao;
        }

        @Override
        protected Void doInBackground(Integer... heroes) {
            mAysncDao.delete(heroes[0]);
            return null;
        }
    }
}
