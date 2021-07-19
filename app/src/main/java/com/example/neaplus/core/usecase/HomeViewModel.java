package com.example.neaplus.core.usecase;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.neaplus.core.domain.NewsRepository;
import com.example.neaplus.core.model.News;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private NewsRepository mRepository;
    private MutableLiveData<News> mAllDataNews;
    private Map<String, String> parameters;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("All News");
        parameters = new HashMap<>();

        parameters.put("country","id");
        parameters.put("category","business");
        parameters.put("apiKey","a038ee0f2c8d4665bd21853c6b634cf8");
        mRepository = new NewsRepository();
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<News> getAllNews() { 
        if(mAllDataNews == null)
            mAllDataNews = mRepository.getAllNews(parameters);
        return mAllDataNews; }
}