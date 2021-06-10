package com.example.tugasfinalpemrogramanmobile.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasfinalpemrogramanmobile.R;
import com.example.tugasfinalpemrogramanmobile.activities.DetailActivity;
import com.example.tugasfinalpemrogramanmobile.adapters.FavoriteAdapter;
import com.example.tugasfinalpemrogramanmobile.data.FavoriteViewModel;
import com.example.tugasfinalpemrogramanmobile.models.ApiModel;

import java.util.List;


public class FavoriteFragment extends Fragment implements FavoriteAdapter.OnItemClickListener {
    private FavoriteViewModel favoriteViewModel;
    private FavoriteAdapter adapter;
    private List<ApiModel> models;

    public FavoriteFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_favorite_list);
        adapter = new FavoriteAdapter(new FavoriteAdapter.FavoriteDiff(), this);

        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        favoriteViewModel.getAll().observe(getViewLifecycleOwner(), tmdbModels -> {
            if (tmdbModels.isEmpty()) {
                Toast.makeText(getActivity(), "Favorite is empty", Toast.LENGTH_LONG).show();
            }
            adapter.submitList(tmdbModels);
            models = tmdbModels;
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        return view;
    }

    @Override
    public void OnItemClick(int pos) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("ID", Integer.parseInt(models.get(pos).getId()));
        startActivity(intent);
    }
}