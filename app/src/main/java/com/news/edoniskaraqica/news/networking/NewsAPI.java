package com.news.edoniskaraqica.news.networking;

import android.content.Intent;

import com.news.edoniskaraqica.news.HomeNewsAdapterDetaje;
import com.news.edoniskaraqica.news.NewsStore;
import com.news.edoniskaraqica.news.NewsStoreDetaje;
import com.news.edoniskaraqica.news.model.Article;
import com.news.edoniskaraqica.news.model.ArticleDetailed;
import com.news.edoniskaraqica.news.model.Artikulli;
import com.news.edoniskaraqica.news.model.GetArticlesResponse;

import java.util.List;
import java.util.Observable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;

/**
 * Created by Edonis Karaçica on 3/27/2017.
 */

public class NewsAPI {
    private static final String APIKEY="12de349c02004428898d4e2b9649ef73";
    private static final String APIPATH="http://edonisk.com/";
//    private List<ArticleDetailed> newsArticles1;
//    int post=0;
//    ArticleDetailed newsArticle1=newsArticles1.get(post);


     private static NewsService newsService=null;

    public static NewsService getApi(){
        if (newsService==null){
            //initialize NewsService
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(APIPATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsService=retrofit.create(NewsService.class);
        }
        return newsService;
    }





    public interface NewsService {
        @GET("autoret3.json")
        Call<GetArticlesResponse> getArticles();

        @GET("{id}.json")
        Call<Artikulli> getArticles1(@Path("id") int id);

        @GET("sport.json")
        Call<GetArticlesResponse> getArticles2();

    }
}
