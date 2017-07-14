package com.news.edoniskaraqica.news;

import com.news.edoniskaraqica.news.model.ArticleDetailed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edonis Karaçica on 6/20/2017.
 */

public class NewsStoreDetaje {
    private static List<ArticleDetailed> newsArticles1=new ArrayList<>();

    public static List<ArticleDetailed> getNewsArticles1() {
        return newsArticles1;
    }

    public static void setNewsArticles1(List<ArticleDetailed> newsArticles1) {
        NewsStoreDetaje.newsArticles1 = newsArticles1;
    }
}
