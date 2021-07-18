package com.example.neaplus.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.neaplus.core.model.Articles;
import com.example.neaplus.core.model.News;

import java.util.ArrayList;

public class HomeListAdapter extends ListAdapter<News, HomeViewHolder>{
    Context context;
    ArrayList<Articles> articles;
    public HomeListAdapter(Context context, ArrayList<Articles> articles, @NonNull DiffUtil.ItemCallback<News> diffCallback) {
        super(diffCallback);
        this.context = context;
        this.articles = articles;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HomeViewHolder.create(parent);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.bind(articles.get(position).getPublishedAt(),articles.get(position).getUlrToImage(),articles.get(position).getTitle(),articles.get(position).getSource().getName(),context);
    }

    static class NewsDiff extends DiffUtil.ItemCallback<News> {
        @Override
        public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
            return oldItem.getArticles().equals(newItem.getArticles());
        }
    }
}
