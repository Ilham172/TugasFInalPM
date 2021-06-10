package com.example.tugasfinalpemrogramanmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tugasfinalpemrogramanmobile.R;
import com.example.tugasfinalpemrogramanmobile.models.ApiModel;
import com.example.tugasfinalpemrogramanmobile.network.Const;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private final List<ApiModel> airingToday;
    private final OnItemClick onItemClick;

    public TvShowAdapter(List<ApiModel> airingToday, OnItemClick onItemClick) {
        this.airingToday = airingToday;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tv_show_layout, parent, false);
        return new ViewHolder(view, onItemClick);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(Const.IMG_URL_200 + airingToday.get(position).getPosterPath())
                .into(holder.ivMovieImg);
        holder.tvMovieTitle.setText(airingToday.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return airingToday.size();
    }

    public interface OnItemClick {
        void onClick(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClick onItemClick;
        ImageView ivMovieImg;
        TextView tvMovieTitle;

        public ViewHolder(View view, OnItemClick onItemClick) {
            super(view);
            view.setOnClickListener(this);
            ivMovieImg = view.findViewById(R.id.iv_airing_today_img);
            tvMovieTitle = view.findViewById(R.id.tv_airing_today_title);
            this.onItemClick = onItemClick;
        }

        @Override
        public void onClick(View v) {
            onItemClick.onClick(getBindingAdapterPosition());
        }
    }

}
