package com.example.neaplus.core.repository;

import com.example.neaplus.core.model.Articles;
import com.example.neaplus.core.resource.ArticlesData;

import java.util.ArrayList;

public class ArticleRepositoryMock {
    ArrayList<Articles> mAllArticle = ArticlesData.getListData();

    public ArrayList<Articles> getAllArticle() {
        return mAllArticle;
    }

    public void insert(Articles article) {
        mAllArticle.add(article);
    }

    public String getArticle(String url){
        for (Articles ar : mAllArticle) {
            if (ar.getUrl().equals(url)) {
                return ar.getTitle();
            }
        }

        return "[NOT FOUND]";
    }

    public void delete(Articles article) {
        String urlParam = article.getUrl();
        int sizeArray = mAllArticle.size();
        for (int i = 0; i < sizeArray; i++) {
            if (mAllArticle.get(i).getUrl().equals(urlParam)) {
                mAllArticle.remove(i);
            }
        }
    }

    public void deleteAll() {
        mAllArticle.clear();
    }

}
