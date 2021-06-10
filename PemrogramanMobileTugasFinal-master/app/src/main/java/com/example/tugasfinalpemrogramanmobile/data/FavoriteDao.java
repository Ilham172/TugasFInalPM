package com.example.tugasfinalpemrogramanmobile.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tugasfinalpemrogramanmobile.models.ApiModel;

import java.util.List;

@Dao
public interface FavoriteDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ApiModel favorite);

    @Insert
    void insertAll(ApiModel... favorites);

    @Delete
    void delete(ApiModel favorite);

    @Query("DELETE FROM favorite_table")
    void deleteAll();

    @Query("SELECT * FROM favorite_table")
    LiveData<List<ApiModel>> getAll();
}
