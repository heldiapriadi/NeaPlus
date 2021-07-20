package com.example.neaplus.core.resource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.neaplus.core.model.database.SourceWithArticle;

import java.util.List;

@Dao
public interface SourceWithArticleDao {
    @Transaction
    @Query("SELECT * FROM source")
    public LiveData<List<SourceWithArticle>> getUsersWithPlaylists();
}
