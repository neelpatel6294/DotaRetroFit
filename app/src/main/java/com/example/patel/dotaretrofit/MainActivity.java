package com.example.patel.dotaretrofit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.patel.dotaretrofit.Adapter.CustomAdapter;
import com.example.patel.dotaretrofit.HeroViewModel.HeroViewModel;
import com.example.patel.dotaretrofit.Model.Heroes;
import com.facebook.stetho.Stetho;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private HeroViewModel mViewModel;
    private final static String MENU_SELECTED = "selected";
    private int selected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        recyclerView = findViewById(R.id.customRecyclerView);


        mViewModel = ViewModelProviders.of(this).get(HeroViewModel.class);

        if (savedInstanceState == null) {
            populateUI(selected);

        } else {
            selected = savedInstanceState.getInt(MENU_SELECTED);
            populateUI(selected);
        }


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int id = (int) viewHolder.itemView.getTag();
                mViewModel.deleteHero(id);

            }
        }).attachToRecyclerView(recyclerView);
    }


    private void generateData(List<Heroes> data) {

        if (data != null) {

            adapter = new CustomAdapter(MainActivity.this, data, new CustomAdapter.ListItemClickListener() {
                @Override
                public void onListItemClick(Heroes movie) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("data", movie);
                    startActivity(intent);
                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "List Null", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(MENU_SELECTED, selected);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.fav_id:

                mViewModel.getFavData();
                selected = 1;
                populateUI(selected);
                break;

            case R.id.allHeroes:
//                mViewModel.getData();
                selected = 0;
                populateUI(selected);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateUI(int i) {

        mViewModel.mLiveData().removeObservers(this);
        switch (i) {
            case 0:
                mViewModel.mLiveData().observe(this, new Observer<List<Heroes>>() {
                    @Override
                    public void onChanged(@Nullable List<Heroes> results) {
                        generateData(results);
                    }
                });
                break;

            case 1:

                mViewModel.mLiveDataFav().observe(this, new Observer<List<Heroes>>() {
                    @Override
                    public void onChanged(@Nullable List<Heroes> results) {
                        generateData(results);
                    }
                });
        }
    }
}
