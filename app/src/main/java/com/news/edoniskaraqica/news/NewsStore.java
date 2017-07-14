package com.news.edoniskaraqica.news;

import com.news.edoniskaraqica.news.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edonis Karaçica on 3/13/2017.
 */

public class NewsStore {
    private static List<Article> newsArticles=new ArrayList<>();

    public static List<Article> getNewsArticles() {
        return newsArticles;
    }

    public static void setNewsArticles(List<Article> newsArticles) {
        NewsStore.newsArticles = newsArticles;
    }
}
