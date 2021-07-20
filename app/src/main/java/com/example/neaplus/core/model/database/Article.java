package com.example.neaplus.core.model.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.neaplus.core.model.Source;

@Entity
public class Article {
    @PrimaryKey
    @NonNull
    private String url;
    @ColumnInfo
    private String author;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String urlToImage;
    @ColumnInfo
    private String publishedAt;
    @ColumnInfo
    private String content;
    @ColumnInfo
    private String source_name;

    public Article(String author, String title, String url, String urlToImage, String publishedAt, String content, String source_name) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.source_name = source_name;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
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

    public String getSource_name() {
        return source_name;
    }

}
