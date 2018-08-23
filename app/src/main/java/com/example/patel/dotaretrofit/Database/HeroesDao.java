package com.example.patel.dotaretrofit.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.patel.dotaretrofit.Model.Heroes;

import java.util.List;

@Dao
public interface HeroesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Heroes heroes);

    @Query("SELECT * FROM heroes_table")
    LiveData<List<Heroes>> getAllFav();

    @Query("DELETE FROM heroes_table WHERE id=:id")
    void delete(int id);
}
