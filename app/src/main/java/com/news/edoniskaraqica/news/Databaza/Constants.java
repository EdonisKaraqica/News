package com.news.edoniskaraqica.news.Databaza;

/**
 * Created by Edonis Karaçica on 7/6/2017.
 */

public class Constants {
    public static final String DB_NAME="lajmetDB";
    public static final int DB_VERSION=1;
    public  static final String TABLE_NAME="artikujt";

    public static final String DROP_QUERY="DROP TABLE IF EXIST "+TABLE_NAME;
    public static final String GET_ARTICLES_QUERY="SELECT * FROM "+TABLE_NAME;

    public static final String ID="id";
    public static final String TITLE="title";
    public static final String DESCRIPTION="description";
    public static final String URL="url";
    public static final String URL_IMAGE="urlToImage";
    public static final String PUBLISHED_AT="publishedAt";


    public static final String CREATE_TABLE_QUERY="CREATE TABLE "+TABLE_NAME+ ""+
            "("+ID+" INTEGER not null,"+
            TITLE + " TEXT not null,"+
            DESCRIPTION + " TEXT not null,"+
            URL +" TEXT not null," +
            PUBLISHED_AT+" TEXT not null)";
}
