package com.example.neaplus.core.model;

public class Articles {
    private Source source;

    private String author;
    private String title;
    private String description;
    private String url;
    private String ulrToImage;
    private String publishedAt;
    private String content;

    public Articles(Source source, String author, String title, String description, String url, String ulrToImage, String publishedAt, String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.ulrToImage = ulrToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public Source getSource() {
        return source;
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

    public String getUlrToImage() {
        return ulrToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }
}
