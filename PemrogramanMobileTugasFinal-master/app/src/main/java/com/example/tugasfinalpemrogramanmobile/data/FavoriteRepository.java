package com.example.tugasfinalpemrogramanmobile.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tugasfinalpemrogramanmobile.models.ApiModel;

import java.util.List;

class FavoriteRepository {
    private final FavoriteDao favoriteDao;
    private final LiveData<List<ApiModel>> favoriteList;

    FavoriteRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        favoriteDao = db.favoriteDao();
        favoriteList = favoriteDao.getAll();
    }

    LiveData<List<ApiModel>> getAll() {
        return favoriteList;
    }

    void insert(ApiModel favorite) {
        AppDatabase.databaseWriteExecutor.execute(() -> favoriteDao.insert(favorite));
    }

    void delete(ApiModel favorite) {
        AppDatabase.databaseWriteExecutor.execute(() -> favoriteDao.delete(favorite));
    }
}
