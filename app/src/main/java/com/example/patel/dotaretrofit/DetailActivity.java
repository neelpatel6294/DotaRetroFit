package com.example.patel.dotaretrofit;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patel.dotaretrofit.HeroViewModel.DetailViewModel;
import com.example.patel.dotaretrofit.HeroViewModel.DetailViewModelFactory;
import com.example.patel.dotaretrofit.Model.Heroes;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


    ImageView mHeroImage;
    TextView mName;
    TextView mStats;
    TextView mAttackType;
    Button mFav;
    DetailViewModel mViewModel;
    Heroes mHeroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mHeroImage = findViewById(R.id.image_view_d);
        mName = findViewById(R.id.hero_name_d);
        mStats = findViewById(R.id.hero_stats_d);
        mAttackType = findViewById(R.id.hero_attackType_d);
        mFav = findViewById(R.id.fav_d);
        final Heroes heroes = getIntent().getParcelableExtra("data");
        mHeroes = heroes;
//        String name = getIntent().getExtras().getString(MainActivity.EXTRA_INTENT);

        Picasso.get().load("https://api.opendota.com" + heroes.getImage()).into(mHeroImage);
        mName.setText(heroes.getName());
        mStats.setText(heroes.getAttribute());
        mAttackType.setText(heroes.getAttackType());

        DetailViewModelFactory factory = new DetailViewModelFactory
                (mHeroes.getId(),(Application)getApplicationContext());
        mViewModel = ViewModelProviders.of(this,factory).get(DetailViewModel.class);

        mFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.insert(mHeroes);
            }
        });
    }
}
