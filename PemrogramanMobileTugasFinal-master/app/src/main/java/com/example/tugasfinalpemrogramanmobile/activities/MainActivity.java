package com.example.tugasfinalpemrogramanmobile.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tugasfinalpemrogramanmobile.R;
import com.example.tugasfinalpemrogramanmobile.data.FavoriteViewModel;
import com.example.tugasfinalpemrogramanmobile.fragments.FavoriteFragment;
import com.example.tugasfinalpemrogramanmobile.fragments.TvShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FavoriteViewModel favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        favoriteViewModel.getAll().observe(this, tmdbModels -> {
        });
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_item_airing);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.bottom_nav_item_airing:
                getSupportActionBar().setTitle("Airing Today");
                fragment = new TvShowFragment("airing");
                break;
            case R.id.bottom_nav_item_popular:
                getSupportActionBar().setTitle("Popular");
                fragment = new TvShowFragment("popular");
                break;
            case R.id.bottom_nav_item_top_rated:
                getSupportActionBar().setTitle("Top Rated");
                fragment = new TvShowFragment("toprated");
                break;
            case R.id.bottom_nav_item_favorite:
                getSupportActionBar().setTitle("Favorite");
                fragment = new FavoriteFragment();
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}