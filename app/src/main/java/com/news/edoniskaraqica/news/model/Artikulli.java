
package com.news.edoniskaraqica.news.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artikulli {

    @SerializedName("articles")
    @Expose
    private List<ArticleDetailed> articles1 = null;

    public List<ArticleDetailed> getArticles1() {
        return articles1;
    }

    public void setArticles(List<ArticleDetailed> articles1) {
        this.articles1 = articles1;
    }

}
