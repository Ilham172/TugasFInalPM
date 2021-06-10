package com.example.tugasfinalpemrogramanmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tugasfinalpemrogramanmobile.R;
import com.example.tugasfinalpemrogramanmobile.models.ApiModel;
import com.example.tugasfinalpemrogramanmobile.network.Const;

public class FavoriteAdapter extends ListAdapter<ApiModel, FavoriteAdapter.FavoriteViewHolder> {
    private final OnItemClickListener onItemClickListener;

    public FavoriteAdapter(@NonNull DiffUtil.ItemCallback<ApiModel> diffCallback, OnItemClickListener onItemClickListener) {
        super(diffCallback);
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite_layout, parent, false);
        return new FavoriteViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        ApiModel current = getItem(position);

        holder.title.setText(current.getName());
        Glide.with(holder.itemView.getContext())
                .load(Const.IMG_URL_200 + current.getPosterPath())
                .into(holder.image);
    }


    public interface OnItemClickListener {
        void OnItemClick(int pos);
    }

    public static class FavoriteDiff extends DiffUtil.ItemCallback<ApiModel> {

        @Override
        public boolean areItemsTheSame(@NonNull ApiModel oldItem, @NonNull ApiModel newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ApiModel oldItem, @NonNull ApiModel newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView image;
        OnItemClickListener onItemClickListener;

        private FavoriteViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            title = itemView.findViewById(R.id.tv_favorite_title);
            image = itemView.findViewById(R.id.iv_favorite_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.OnItemClick(getBindingAdapterPosition());
        }
    }
}



