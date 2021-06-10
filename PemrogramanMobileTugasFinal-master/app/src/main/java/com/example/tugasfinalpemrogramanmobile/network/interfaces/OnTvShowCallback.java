package com.example.tugasfinalpemrogramanmobile.network.interfaces;

import com.example.tugasfinalpemrogramanmobile.models.ApiModel;

import java.util.List;

public interface OnTvShowCallback {
    void onSuccess(List<ApiModel> model, int page, String message);
    void onFailure(String message);
}
