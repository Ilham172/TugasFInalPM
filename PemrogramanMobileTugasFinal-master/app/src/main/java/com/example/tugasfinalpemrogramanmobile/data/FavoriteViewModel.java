package com.example.tugasfinalpemrogramanmobile.data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tugasfinalpemrogramanmobile.models.ApiModel;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private final FavoriteRepository mRepository;

    private final LiveData<List<ApiModel>> mAllWords;

    public FavoriteViewModel(Application application) {
        super(application);
        mRepository = new FavoriteRepository(application);
        mAllWords = mRepository.getAll();
    }

    public LiveData<List<ApiModel>> getAll() {
        return mAllWords;
    }

    public void insert(ApiModel favorite) {
        mRepository.insert(favorite);
    }

    public void delete(ApiModel favorite) {
        mRepository.delete((favorite));
    }
}
