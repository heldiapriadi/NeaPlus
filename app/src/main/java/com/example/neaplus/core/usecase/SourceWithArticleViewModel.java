package com.example.neaplus.core.usecase;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.neaplus.core.domain.SourceRepository;
import com.example.neaplus.core.domain.SourceWithArticleRepository;
import com.example.neaplus.core.model.database.Source;
import com.example.neaplus.core.model.database.SourceWithArticle;

import java.util.List;

public class SourceWithArticleViewModel extends AndroidViewModel {
    private SourceWithArticleRepository mRepository;
    private LiveData<List<SourceWithArticle>> mALlSourceWithArticle;

    public SourceWithArticleViewModel(Application application){
        super(application);
        mRepository = new SourceWithArticleRepository(application);
        mALlSourceWithArticle = mRepository.getAllSourceWithArticle();
    }

    public LiveData<List<SourceWithArticle>> getAllArticle() {
        return mALlSourceWithArticle;
    }
}
