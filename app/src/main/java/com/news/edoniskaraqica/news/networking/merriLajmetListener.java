package com.news.edoniskaraqica.news.networking;

import com.news.edoniskaraqica.news.model.Article;

import java.util.List;

/**
 * Created by Edonis Karaçica on 7/6/2017.
 */

public interface merriLajmetListener {
    void onDeliverAllNews(List<Article> articles);

    void OnDeliverArticle(Article article);

    void  onHideDialog();
}
