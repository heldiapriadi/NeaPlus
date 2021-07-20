package com.example.neaplus.core.model.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SourceWithArticle {
    @Embedded public Source source;
    @Relation(
            parentColumn = "id_source",
            entityColumn = "id_source"
    )
    public List<Article> articles;
}
