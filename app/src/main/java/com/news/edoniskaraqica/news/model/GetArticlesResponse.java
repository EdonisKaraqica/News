
package com.news.edoniskaraqica.news.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetArticlesResponse {


    @SerializedName("articles")
    @Expose
    private ArrayList<Article> articles = null;



    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

}
