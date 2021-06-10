package com.example.tugasfinalpemrogramanmobile.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.tugasfinalpemrogramanmobile.R;
import com.example.tugasfinalpemrogramanmobile.data.FavoriteViewModel;
import com.example.tugasfinalpemrogramanmobile.models.ApiModel;
import com.example.tugasfinalpemrogramanmobile.network.ApiRepository;
import com.example.tugasfinalpemrogramanmobile.network.Const;
import com.example.tugasfinalpemrogramanmobile.network.interfaces.OnDetailCallback;

public class DetailActivity extends AppCompatActivity {
    private FavoriteViewModel favoriteViewModel;
    private ImageView ivDetailImage;
    private ImageView ivBackdropImage;
    private TextView tvDetailTitle;
    private TextView tvDetailDescription;
    private TextView tvRating;
    private TextView tvGenres;
    private TextView tvEpisodeAndSeason;
    private TextView tvFirstAndLastAir;
    private ApiModel mModel;
    private boolean isFavorite;
    private ApiRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        repository = ApiRepository.getInstance();
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);

        int id = getIntent().getIntExtra("ID", 0);
        getData(id);
        favoriteViewModel.getAll().observe(this, tmdbModels -> {
            for (ApiModel model1 : tmdbModels) {
                if (Integer.parseInt(model1.getId()) == id) {
                    isFavorite = true;
                }
            }
        });

        ivDetailImage = findViewById(R.id.iv_detail_image);
        ivBackdropImage = findViewById(R.id.iv_detail_backdrop);
        tvDetailTitle = findViewById(R.id.tv_detail_title);
        tvDetailDescription = findViewById(R.id.tv_detail_description);
        tvRating = findViewById(R.id.tv_detail_rating);
        tvGenres = findViewById(R.id.tv_detail_genres);
        tvEpisodeAndSeason = findViewById(R.id.tv_detail_episode_season);
        tvFirstAndLastAir = findViewById(R.id.tv_detail_first_and_last_air);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void getData(int id) {
        repository.getDetail(id, new OnDetailCallback() {
            @Override
            public void onSuccess(ApiModel model, String message) {
                mModel = model;
                onStartBindView(model);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(DetailActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    void onStartBindView(ApiModel model) {
        getSupportActionBar().setTitle(model.getName());
        Glide.with(DetailActivity.this)
                .load(Const.IMG_URL_500 + model.getBackdropPath())
                .into(ivBackdropImage);
        Glide.with(DetailActivity.this)
                .load(Const.IMG_URL_200 + model.getPosterPath())
                .into(ivDetailImage);
        String rating = "Rating: " + model.getVoteAverage();
        String firstAndLastAir = "First air date: " + model.getFirstAirDate()
                + "\nLast air date: " + model.getLastAirDate();
        String episodeAndSeason = "season: " + model.getNumberOfSeasons()
                + "\nEpisode: " + model.getNumberOfEpisodes();
        String genres = "Genres: ";
        if (model.getGenres().isEmpty()) {
            genres += "";
        } else {
            genres += model.getGenres().get(0).getName();
        }
        tvDetailTitle.setText(model.getName());
        tvDetailDescription.setText(model.getOverview());
        tvRating.setText(rating);
        tvGenres.setText(genres);
        tvEpisodeAndSeason.setText(episodeAndSeason);
        tvFirstAndLastAir.setText(firstAndLastAir);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_detail_action_bar, menu);
        MenuItem item = menu.getItem(0);
        if (isFavorite) {
            item.setIcon(R.drawable.ic_favorite_checked);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_bar_favorite_item) {
            if (!isFavorite) {
                addToFavorite();
                item.setIcon(R.drawable.ic_favorite_checked);
            } else {
                deleteFromFavorite();
                isFavorite = false;
                item.setIcon(R.drawable.ic_fav);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addToFavorite() {
        favoriteViewModel.insert(mModel);
        Toast.makeText(DetailActivity.this, "Added to Favorite", Toast.LENGTH_SHORT).show();
    }

    private void deleteFromFavorite() {
        favoriteViewModel.delete(mModel);
        Toast.makeText(DetailActivity.this, "Removed from Favorite", Toast.LENGTH_SHORT).show();

    }
}
