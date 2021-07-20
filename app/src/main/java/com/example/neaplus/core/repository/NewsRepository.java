package com.example.neaplus.core.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.neaplus.core.model.News;
import com.example.neaplus.core.resource.JsonPlaceHolderApi;
import com.example.neaplus.core.resource.RetrofitService;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private JsonPlaceHolderApi newsAPI;
    private LiveData<List<News>> mAllDataNews;
    private static NewsRepository newsRepository;

    public NewsRepository() {
        newsAPI = RetrofitService.createService(JsonPlaceHolderApi.class);
    }

    public static NewsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    public MutableLiveData<News> getAllNews(Map<String, String> source) {
        MutableLiveData<News> newsData = new MutableLiveData<>();
        newsAPI.getNews(source).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
