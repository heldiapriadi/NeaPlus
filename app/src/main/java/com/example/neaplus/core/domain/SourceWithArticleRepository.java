package com.example.neaplus.core.domain;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.neaplus.core.model.database.Article;
import com.example.neaplus.core.model.database.SourceWithArticle;
import com.example.neaplus.core.resource.NeaDatabase;
import com.example.neaplus.core.resource.SourceWithArticleDao;

import java.util.List;

public class SourceWithArticleRepository {

    private SourceWithArticleDao sourceWithArticleDao;
    private LiveData<List<SourceWithArticle>> mAllSourceWithArticle;

    public SourceWithArticleRepository(Application application){
        NeaDatabase db = NeaDatabase.getDatabase(application);
        sourceWithArticleDao = db.sourceWithArticleDao();
        mAllSourceWithArticle = sourceWithArticleDao.getUsersWithPlaylists();
    }

    public LiveData<List<SourceWithArticle>> getAllSourceWithArticle() {
        return mAllSourceWithArticle;
    }
}
