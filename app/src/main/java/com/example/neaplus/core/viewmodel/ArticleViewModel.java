package com.example.neaplus.core.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.neaplus.core.repository.ArticleRepository;
import com.example.neaplus.core.model.database.Article;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository mRepository;
    private LiveData<List<Article>> mAllArticle;

    public ArticleViewModel(Application application) {
        super(application);
        mRepository = new ArticleRepository(application);
        mAllArticle = mRepository.getAllArticle();
    }

    public LiveData<List<Article>> getmAllArticle() {
        return mAllArticle;
    }

    public void insert(Article article) {
        mRepository.insert(article);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void delete(Article article) {
        mRepository.delete(article);
    }
}
