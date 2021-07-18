package com.example.neaplus.core.model;

import java.util.List;

public class News {
    private String status;
    
    private int totalResults;

    private List<Articles> articles;

    public News(String status, int totalResults, List<Articles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }
}
