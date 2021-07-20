package com.example.neaplus.core.domain;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.neaplus.core.model.database.Article;
import com.example.neaplus.core.resource.ArticleDao;
import com.example.neaplus.core.resource.NeaDatabase;

import java.util.List;

public class ArticleRepository {
    private ArticleDao articleDao;
    private LiveData<List<Article>> mAllArticle;

    public ArticleRepository(Application application){
        NeaDatabase db = NeaDatabase.getDatabase(application);
        articleDao = db.articleDao();
        mAllArticle =articleDao.getAllArticle();
    }

    public LiveData<List<Article>> getAllArticle() {
        return mAllArticle;
    }

    public void insert(Article article){
        NeaDatabase.databaseWriteExecutor.execute(() -> {
            articleDao.insert(article);
        });
    }

    public void deleteAll(){
        NeaDatabase.databaseWriteExecutor.execute(() -> {
            articleDao.deleteAll();
        });
    }

    public void delete(Article article){
        NeaDatabase.databaseWriteExecutor.execute(() -> {
            articleDao.delete(article);
        });
    }
}
