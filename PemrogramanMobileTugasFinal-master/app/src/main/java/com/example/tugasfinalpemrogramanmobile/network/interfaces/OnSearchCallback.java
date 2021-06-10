package com.example.tugasfinalpemrogramanmobile.network.interfaces;

import com.example.tugasfinalpemrogramanmobile.models.ApiModel;

import java.util.List;

public interface OnSearchCallback {
    void onSuccess(List<ApiModel> model, String message);
    void onFailure(String message);
}
