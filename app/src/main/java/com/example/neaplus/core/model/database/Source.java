package com.example.neaplus.core.model.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Source {
    @PrimaryKey
    @NonNull
    private int id_source;
    @ColumnInfo
    private String id;
    @ColumnInfo
    private String name;

    public int getId_source() {
        return id_source;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Source(int id_source, String id, String name) {
        this.id_source = id_source;
        this.id = id;
        this.name = name;
    }
}
