package com.example.tugasfinalpemrogramanmobile.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasfinalpemrogramanmobile.R;
import com.example.tugasfinalpemrogramanmobile.activities.DetailActivity;
import com.example.tugasfinalpemrogramanmobile.adapters.TvShowAdapter;
import com.example.tugasfinalpemrogramanmobile.models.ApiModel;
import com.example.tugasfinalpemrogramanmobile.network.ApiRepository;
import com.example.tugasfinalpemrogramanmobile.network.interfaces.OnSearchCallback;
import com.example.tugasfinalpemrogramanmobile.network.interfaces.OnTvShowCallback;

import java.util.ArrayList;
import java.util.List;

public class TvShowFragment extends Fragment implements TvShowAdapter.OnItemClick,
        SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private TvShowAdapter adapter;
    private List<ApiModel> airingToday;
    private final String type;
    private ApiRepository repository;
    private int currentPage;

    public TvShowFragment(String type) {
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = ApiRepository.getInstance();
        airingToday = new ArrayList<>();
        adapter = new TvShowAdapter(airingToday, this);
        currentPage = 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show_list, container, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2,
                GridLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.rv_airing_today_list);
        recyclerView.setLayoutManager(gridLayoutManager);
        loadData(getType(), currentPage);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int itemCount = gridLayoutManager.getItemCount();
                    int visibleItem = gridLayoutManager.getChildCount();
                    int firstVisibleItem = gridLayoutManager.
                            findFirstCompletelyVisibleItemPosition();
                    if (visibleItem + firstVisibleItem >= itemCount) {
                        currentPage += 1;
                        loadData(getType(), currentPage);
                    }
                }
            }
        });
        setHasOptionsMenu(true);
        return view;
    }

    public void loadData(String type, int page) {
        repository.getTvShow(type, page, new OnTvShowCallback() {
            @Override
            public void onSuccess(List<ApiModel> model, int page, String message) {
                airingToday.addAll(model);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(int pos) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("ID", Integer.parseInt(airingToday.get(pos).getId()));
        startActivity(intent);
    }

    public String getType() {
        return type;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_activity_menu, menu);
        MenuItem search = menu.findItem(R.id.search_item);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.length() > 0) {
            airingToday = new ArrayList<>();
            search(newText);
        } else {
            airingToday = new ArrayList<>();
            adapter = new TvShowAdapter(airingToday, this);
            loadData(getType(), 1);
            recyclerView.setAdapter(adapter);
        }
        return true;
    }

    void search(String text) {
        repository.search(text, new OnSearchCallback() {
            @Override
            public void onSuccess(List<ApiModel> model, String message) {
                airingToday.addAll(model);
                adapter = new TvShowAdapter(airingToday, TvShowFragment.this);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}