package com.example.tugasfinalpemrogramanmobile.data;

import androidx.room.TypeConverter;

import com.example.tugasfinalpemrogramanmobile.models.GenreModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<GenreModel> fromString(String value) {
        Type listType = new TypeToken<ArrayList<GenreModel>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<GenreModel> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
