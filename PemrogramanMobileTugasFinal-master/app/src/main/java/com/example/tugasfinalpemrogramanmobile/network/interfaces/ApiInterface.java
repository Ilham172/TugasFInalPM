package com.example.tugasfinalpemrogramanmobile.network.interfaces;

import com.example.tugasfinalpemrogramanmobile.models.ApiModel;
import com.example.tugasfinalpemrogramanmobile.network.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("tv/popular")
    Call<ApiResponse> getPopular(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("tv/top_rated")
    Call<ApiResponse> getToprated(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("tv/airing_today")
    Call<ApiResponse> getAiring(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("tv/{id}")
    Call<ApiModel> getTv(
            @Path("id") int id,
            @Query("api_key") String apiKey
    );

    @GET("search/tv")
    Call<ApiResponse> search(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );
}
