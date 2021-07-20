package com.example.neaplus.core.usecase;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.neaplus.core.domain.ArticleRepository;
import com.example.neaplus.core.model.database.Article;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository mRepository;
    private LiveData<List<Article>> mAllArticle;

    public ArticleViewModel(Application application){
        super(application);
        mRepository = new ArticleRepository(application);
        mAllArticle = mRepository.getAllArticle();
    }

    public LiveData<List<Article>> getmAllArticle() {
        return mAllArticle;
    }

    void insert(Article article){
        mRepository.insert(article);
    }

    void deleteAll(){
        mRepository.deleteAll();;
    }

    void delete(Article article){
        mRepository.delete(article);
    }
}
