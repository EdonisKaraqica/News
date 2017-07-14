package com.news.edoniskaraqica.news.Databaza;
import com.news.edoniskaraqica.news.BallinaFragment;
import com.news.edoniskaraqica.news.MainActivity;
import com.news.edoniskaraqica.news.model.Article;
import com.news.edoniskaraqica.news.networking.merriLajmetListener;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Looper;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.news.edoniskaraqica.news.model.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

/**
 * Created by Edonis Karaçica on 7/6/2017.
 */

public class Databaza extends SQLiteOpenHelper {

    private static final String TAG=Databaza.class.getSimpleName();


    Context c;
    public Databaza(Context context) {
        super( context, Constants.DB_NAME,null,Constants.DB_VERSION);
        c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL( Constants.CREATE_TABLE_QUERY );
        }catch (SQLException ex){
            Log.d(TAG,ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL( Constants.DROP_QUERY );
        this.onCreate( sqLiteDatabase );

    }
    public void shtoLajme(Article article){
        //Log.d( TAG,"Values Got "+article.getTitle() );
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( Constants.ID,article.getId());
        values.put( Constants.TITLE,article.getTitle() );
        values.put( Constants.DESCRIPTION,article.getDescription() );
        //values.put(Constants.URL,article.getUrl() );
        //values.put( Constants.URL_IMAGE,article.getUrlToImage()  );
        values.put( Constants.PUBLISHED_AT,article.getPublishedAt() );

        try {

            db.insert( Constants.TABLE_NAME,null,values );
            Toast.makeText( c,"sukses",Toast.LENGTH_SHORT ).show();



            }catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
       // Log.d( TAG , String.valueOf( i ) );
        db.close();

    }
    public void merriLajmet(merriLajmetListener listener){
        marrsiLajmeve marrsi=new marrsiLajmeve( listener, this.getWritableDatabase() );
        marrsi.start();
        
    }
    public class marrsiLajmeve extends Thread{
        private final merriLajmetListener mListener;
        private final SQLiteDatabase mDb;

        public marrsiLajmeve(merriLajmetListener mListener, SQLiteDatabase mDb) {
            this.mListener = mListener;
            this.mDb = mDb;
        }

        @Override
        public void run() {
            Cursor cursor=mDb.rawQuery( Constants.GET_ARTICLES_QUERY,null );

            final List<Article> articleList=new ArrayList<>(  );

            if (cursor.getCount()>0){
                if (cursor.moveToFirst()){
                    do {
                        Article article=new Article();
                        article.setId( cursor.getString( cursor.getColumnIndex( Constants.ID ) ) );
                        article.setTitle( cursor.getString( cursor.getColumnIndex( Constants.TITLE ) ) );
                        article.setDescription( cursor.getColumnName( cursor.getColumnIndex( Constants.DESCRIPTION ) ) );
                        article.setPublishedAt( cursor.getColumnName( cursor.getColumnIndex( Constants.PUBLISHED_AT ) ) );
                        articleList.add(article);
                        publishNews(article);
                    } while (cursor.moveToNext());
                }
            }
            android.os.Handler handler = new android.os.Handler( Looper.getMainLooper());


            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onDeliverAllNews(articleList);
                    mListener.onHideDialog();
                }
            });
        }
        public void publishNews(final Article article) {
            android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.OnDeliverArticle(article);
                }
            });
        }
    }
}
