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
import com.example.neaplus.ui.DetailActivity;
import com.example.neaplus.ui.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeListAdapter extends ListAdapter<News, HomeViewHolder> {
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
        Date date1 = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(articles.get(position).getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.bind(sdf1.format(date1), articles.get(position).getUrlToImage(), articles.get(position).getTitle(), articles.get(position).getSource().getName(), articles.get(position).getUrl(), context);
        holder.titleItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", articles.get(position).getTitle());
                bundle.putString("author", articles.get(position).getAuthor());
                bundle.putString("publisher", articles.get(position).getSource().getName());
                bundle.putString("published", articles.get(position).getPublishedAt());
                bundle.putString("image", articles.get(position).getUrlToImage());
                bundle.putString("content", articles.get(position).getContent());
                bundle.putString("url", articles.get(position).getUrl());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
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
