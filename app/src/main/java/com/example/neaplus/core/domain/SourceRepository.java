package com.example.neaplus.core.domain;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.neaplus.core.model.database.Article;
import com.example.neaplus.core.model.database.Source;
import com.example.neaplus.core.resource.ArticleDao;
import com.example.neaplus.core.resource.NeaDatabase;
import com.example.neaplus.core.resource.SourceDao;

import java.util.List;

public class SourceRepository {
    private SourceDao sourceDao;
    private LiveData<List<Source>> mAllSource;

    public SourceRepository(Application application){
        NeaDatabase db = NeaDatabase.getDatabase(application);
        sourceDao = db.sourceDao();
        mAllSource = sourceDao.getAllSource();
    }

    public LiveData<List<Source>> getmAllSource() {
        return mAllSource;
    }

    public void insert(Source source){
        NeaDatabase.databaseWriteExecutor.execute(() -> {
            sourceDao.insert(source);
        });
    }

    public void deleteAll(){
        NeaDatabase.databaseWriteExecutor.execute(() -> {
            sourceDao.deleteAll();
        });
    }

    public void delete(Source source){
        NeaDatabase.databaseWriteExecutor.execute(() -> {
            sourceDao.delete(source);
        });
    }
}
