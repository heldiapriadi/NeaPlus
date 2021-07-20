package com.example.neaplus.core.resource;

import com.example.neaplus.core.model.News;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {
    @GET("top-headlines")
    Call<News> getNews(@QueryMap Map<String, String> parameters);
}


