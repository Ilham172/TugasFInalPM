package com.example.tugasfinalpemrogramanmobile.network;

import androidx.annotation.NonNull;

import com.example.tugasfinalpemrogramanmobile.models.ApiModel;
import com.example.tugasfinalpemrogramanmobile.network.interfaces.ApiInterface;
import com.example.tugasfinalpemrogramanmobile.network.interfaces.OnDetailCallback;
import com.example.tugasfinalpemrogramanmobile.network.interfaces.OnSearchCallback;
import com.example.tugasfinalpemrogramanmobile.network.interfaces.OnTvShowCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRepository {
    private static ApiRepository repository;
    private final ApiInterface apiInterface;

    private ApiRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public static ApiRepository getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Const.BASE_URL_DETAIL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return new ApiRepository(retrofit.create(ApiInterface.class));
        }
        return repository;
    }

    public void getDetail(int id, OnDetailCallback callback) {
        apiInterface.getTv(id, Const.API_KEY).enqueue(new Callback<ApiModel>() {

            @Override
            public void onResponse(@NonNull Call<ApiModel> call, @NonNull Response<ApiModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body(), response.message());
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiModel> call, @NonNull Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void getTvShow(String type, int page, OnTvShowCallback onTvShowCallback) {

        Call<ApiResponse> nowPlayingCall = apiInterface.getAiring(Const.API_KEY, page);
        switch (type) {
            case "airing":
                nowPlayingCall = apiInterface.getAiring(Const.API_KEY, page);
                break;
            case "popular":
                nowPlayingCall = apiInterface.getPopular(Const.API_KEY, page);
                break;
            case "toprated":
                nowPlayingCall = apiInterface.getToprated(Const.API_KEY, page);
                break;
        }

        nowPlayingCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body().getModel() != null) {
                    onTvShowCallback.onSuccess(response.body().getModel(), page, response.message());
                } else {
                    onTvShowCallback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                onTvShowCallback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void search(String query, OnSearchCallback onSearchCallback) {
        apiInterface.search(Const.API_KEY, query).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body().getModel() != null) {
                    onSearchCallback.onSuccess(response.body().getModel(), response.message());
                } else {
                    onSearchCallback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                onSearchCallback.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
