package com.news.edoniskaraqica.news;

import android.support.v4.view.MenuCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.news.edoniskaraqica.news.model.ArticleDetailed;
import com.news.edoniskaraqica.news.model.Artikulli;
import com.news.edoniskaraqica.news.networking.NewsAPI;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class LajmiDetajuar1 extends AppCompatActivity {
    private static final String KEY_INDEX="news_index";
    private RecyclerView newsRecyclerView;
    private ShareActionProvider mShareActionProvider;
    HomeNewsAdapterDetaje homeNewsAdapter1;
    private List<ArticleDetailed> newsArticles1;
    public String url="";
    private WebView webView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lajmi_detajuar1);
        SimpleDateFormat smpData=new SimpleDateFormat("hh:mm");
        final Button btn=(Button)findViewById( R.id.btnKomenti );
        btn.setVisibility( View.INVISIBLE );


        //setShareIntent( getIntent() );

       // webView=(WebView)findViewById( R.id.activity_news_details_webview );

        newsRecyclerView=(RecyclerView)findViewById(R.id.activity_main_recyclerview1);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        retrofit2.Call<Artikulli> call;
       //int index=getIntent().getIntExtra(KEY_INDEX,1);


        int index=getIntent().getIntExtra(KEY_INDEX,-1);
        //index=index-1;
        //arsyeja pse po fillojme prej index - 1 eshte se te klasa newsStore numerimi fillon prej 0

        call = NewsAPI.getApi().getArticles1(index);
        url=NewsStore.getNewsArticles().get( index-1).getUrl();
        //url=NewsStoreDetaje.getNewsArticles1().get( index ).getUrl();

        //call mundet mu thirr sinkronsilli ose asinkronislli, po masi e thirrim prej thredit kryesor atehere eshte asinkroslli
        call.enqueue(new Callback<Artikulli>() {
            @Override
            public void onResponse(retrofit2.Call<Artikulli> call, Response<Artikulli> response) {
                Artikulli getArticlesResponse1=response.body();
                NewsStoreDetaje.setNewsArticles1(getArticlesResponse1.getArticles1());
                //Toast.makeText(LajmiDetajuar1.this,"Sukses",Toast.LENGTH_SHORT).show();
                homeNewsAdapter1=new HomeNewsAdapterDetaje(getArticlesResponse1.getArticles1());
                newsRecyclerView.setAdapter(homeNewsAdapter1);

                btn.setVisibility( View.VISIBLE );


            }

            @Override
            public void onFailure(retrofit2.Call<Artikulli> call, Throwable t) {
                Toast.makeText(LajmiDetajuar1.this,"Error received",Toast.LENGTH_SHORT).show();

            }
        });


        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( LajmiDetajuar1.this,KomentetActivity.class );
                startActivity( intent );
            }
        } );








    }


    public static void launch1(Context context, int index){
        Intent intent=new Intent(context,LajmiDetajuar1.class);
        intent.putExtra(KEY_INDEX,index);
        context.startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.share_action, menu);
        MenuItem item = menu.findItem(R.id.menu_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        mShareActionProvider.setShareIntent(doShare());
        return true;
    }
    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
    public Intent doShare() {
        // populate the share intent with data
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,url);
        return intent;
    }

}
