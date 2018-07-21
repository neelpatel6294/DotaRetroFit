package com.example.patel.dotaretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.patel.dotaretrofit.Adapter.CustomAdapter;
import com.example.patel.dotaretrofit.Application.MyApplication;
import com.example.patel.dotaretrofit.Model.Heroes;
import com.example.patel.dotaretrofit.Network.GetDataService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    private CustomAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MyApplication) getApplication()).getApiComponent().inject(this);


        GetDataService service = retrofit.create(GetDataService.class);
        Call<List<Heroes>> call = service.getAllData();
        call.enqueue(new Callback<List<Heroes>>() {
            @Override
            public void onResponse(Call<List<Heroes>> call, Response<List<Heroes>> response) {
                generateData(response.body());

            }
            @Override
            public void onFailure(Call<List<Heroes>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

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
