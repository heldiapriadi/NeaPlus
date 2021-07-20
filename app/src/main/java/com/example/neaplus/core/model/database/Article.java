package com.example.neaplus.core.model.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.neaplus.core.model.Source;

@Entity
public class Article {
    public int getId_article() {
        return id_article;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public int getId_source() {
        return id_source;
    }

    @PrimaryKey
    @NonNull
    private int id_article;
    @ColumnInfo
    private String author;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String description;

    public Article(int id_article, String author, String title, String description, String url, String urlToImage, String publishedAt, String content, int id_source) {
        this.id_article = id_article;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.id_source = id_source;
    }

    @ColumnInfo
    private String url;
    @ColumnInfo
    private String urlToImage;
    @ColumnInfo
    private String publishedAt;
    @ColumnInfo
    private String content;

    @ColumnInfo
    private int id_source;
}
