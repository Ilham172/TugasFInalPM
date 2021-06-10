package com.example.tugasfinalpemrogramanmobile.network.interfaces;

import com.example.tugasfinalpemrogramanmobile.models.ApiModel;

public interface OnDetailCallback {
    void onSuccess(ApiModel model, String message);
    void onFailure(String message);
}
