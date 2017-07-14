package com.news.edoniskaraqica.news;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class KomentetActivity extends AppCompatActivity {

    private WebView webDisqus;
    private Button btnShfletuesi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_komentet );

        //set layout to disqus xml
        String index=NewsStoreDetaje.getNewsArticles1().get( 0 ).getId();
        String title=NewsStoreDetaje.getNewsArticles1().get( 0 ).getTitle();

        webDisqus = (WebView) findViewById(R.id.activity_komentet_webview);
        //set up disqus
        WebSettings webSettings2 = webDisqus.getSettings();

        webSettings2.setJavaScriptEnabled(true);

        webSettings2.setBuiltInZoomControls(false);

        webDisqus.requestFocusFromTouch();

        webDisqus.setWebViewClient(new WebViewClient());

        webDisqus.setWebChromeClient(new WebChromeClient());

        webDisqus.loadUrl("http://www.edonisk.com/komentet.php?id="+index+"&title="+title);


//        WebView webView=(WebView)findViewById( R.id.activity_komentet_webview );
//        webView.getSettings().setJavaScriptEnabled( true );
//        webView.setWebViewClient( new WebViewClient() );
//        webView.loadUrl( "http://www.edonisk.com" );


        btnShfletuesi=(Button)findViewById( R.id.btnShfletuesi );
        btnShfletuesi.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String index=NewsStoreDetaje.getNewsArticles1().get( 0 ).getId();
                String title=NewsStoreDetaje.getNewsArticles1().get( 0 ).getTitle();
                startActivity(new Intent(Intent.ACTION_VIEW).setData( Uri.parse("http://www.edonisk.com/komentet.php?id="+index+"&title="+title)));
            }
        } );
    }
}
