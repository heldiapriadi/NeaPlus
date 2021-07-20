package com.example.neaplus.core.resource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.neaplus.core.model.database.Source;

import java.util.List;

@Dao
public interface SourceDao {

    @Query("SELECT * FROM source")
    LiveData<List<Source>> getAllSource();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Source source);

    @Query("DELETE FROM Source")
    void deleteAll();

    @Delete
    void delete(Source souce);
}
