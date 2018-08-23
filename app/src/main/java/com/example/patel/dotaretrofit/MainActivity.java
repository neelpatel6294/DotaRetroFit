package com.example.patel.dotaretrofit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.patel.dotaretrofit.Adapter.CustomAdapter;
import com.example.patel.dotaretrofit.HeroViewModel.HeroViewModel;
import com.example.patel.dotaretrofit.Model.Heroes;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private HeroViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mViewModel = ViewModelProviders.of(this).get(HeroViewModel.class);
        mViewModel.mLiveData().observe(this, new Observer<List<Heroes>>() {
            @Override
            public void onChanged(@Nullable List<Heroes> heroes) {
                generateData(heroes);
            }
        });

    }

    private void generateData(List<Heroes> data) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(data, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
