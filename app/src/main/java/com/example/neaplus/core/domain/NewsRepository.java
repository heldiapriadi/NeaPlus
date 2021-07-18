package com.example.neaplus.core.domain;

import android.app.Application;

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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRepository {
    private JsonPlaceHolderApi newsAPI;
    private LiveData<List<News>> mAllDataNews;

    public NewsRepository() {
        newsAPI =  RetrofitService.createService(JsonPlaceHolderApi.class);
    }

    public MutableLiveData<News> getAllNews(Map<String, String> source){
        MutableLiveData<News> newsData = new MutableLiveData<>();
        newsAPI.getNews(source).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful()){
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
