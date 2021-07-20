package com.example.neaplus.core.usecase;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.neaplus.core.domain.ArticleRepository;
import com.example.neaplus.core.domain.SourceRepository;
import com.example.neaplus.core.model.database.Source;

import java.util.List;

public class SourceViewModel  extends AndroidViewModel {
    private SourceRepository mRepository;
    private LiveData<List<Source>> mALlSource;

    public SourceViewModel(Application application){
        super(application);
        mRepository = new SourceRepository(application);
        mALlSource = mRepository.getmAllSource();
    }

    public LiveData<List<Source>> getmAllArticle() {
        return mALlSource;
    }

    void insert(Source source){
        mRepository.insert(source);
    }

    void deleteAll(){
        mRepository.deleteAll();;
    }

    void delete(Source source){
        mRepository.delete(source);
    }
}
