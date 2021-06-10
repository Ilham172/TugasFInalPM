package com.example.tugasfinalpemrogramanmobile.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity(tableName = "favorite_table")
public class ApiModel {
    @SerializedName("backdrop_path")
    private String backdropPath;

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    private String id;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("last_air_date")
    private String lastAirDate;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @SerializedName("number_of_episodes")
    private String numberOfEpisodes;

    @SerializedName("number_of_seasons")
    private String numberOfSeasons;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("vote_average")
    private String voteAverage;

    @ColumnInfo(name = "is_favorite")
    private boolean isFavorite;

    @ColumnInfo(name = "genres")
    private ArrayList<GenreModel> genres;

    public ApiModel() {

    }

    public ArrayList<GenreModel> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<GenreModel> genres) {
        this.genres = genres;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(String numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public String getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(String numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}


