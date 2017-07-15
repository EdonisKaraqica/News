package com.news.edoniskaraqica.news.Fragmentet;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.news.edoniskaraqica.news.Databaza.Databaza;
import com.news.edoniskaraqica.news.Databaza.Utilis;
import com.news.edoniskaraqica.news.HomeNewsAdapter;
import com.news.edoniskaraqica.news.LajmiDetajuar1;
import com.news.edoniskaraqica.news.NewsStore;
import com.news.edoniskaraqica.news.R;
import com.news.edoniskaraqica.news.model.Article;
import com.news.edoniskaraqica.news.model.ArticleDetailed;
import com.news.edoniskaraqica.news.model.Artikulli;
import com.news.edoniskaraqica.news.model.GetArticlesResponse;
import com.news.edoniskaraqica.news.networking.NewsAPI;
import com.news.edoniskaraqica.news.networking.merriLajmetListener;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Filter;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BallinaFragment extends Fragment implements SearchView.OnQueryTextListener, merriLajmetListener{
    private static final String KEY_INDEX = "news_index";
    private RecyclerView newsRecyclerView;
    private HomeNewsAdapter homeNewsAdapter;
    private Databaza mDatabaza;

   // private Databaza mDatabaza=new Databaza(  getActivity().getApplicationContext());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_ballina,null );
        newsRecyclerView = (RecyclerView) rootView.findViewById( R.id.activity_main_recyclerview_ballina );
        newsRecyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        //setContentView(R.layout.activity_lajmi_detajuar1);
        setHasOptionsMenu( true );
        SimpleDateFormat smpData = new SimpleDateFormat( "hh:mm" );
        Databaza mDatabaza=new Databaza( getActivity());

        //mDatabaza=new Databaza(getActivity() );


        //newsRecyclerView=findViewById(R.id.activity_main_recyclerview_ballina);
        // newsRecyclerView=(RecyclerView) newsRecyclerView.findViewById(R.id.activity_main_recyclerview_ballina);
        //newsRecyclerView.setLayoutManager(new LinearLayoutManager());
        //newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //newsRecyclerView=getView().findViewById();


        //retrofit2.Call<Artikulli> call;
        //int index=getIntent().getIntExtra(KEY_INDEX,1);
        if(getNetworkAvailability()){
            getFeed();
        }
        else{
            getFeedFromDatabase();
            //Toast.makeText( getActivity(),"Ju lutem kyçuni ne internet :)",Toast.LENGTH_SHORT ).show();
        }





    }
    public void getFeed(){
        final int index = getActivity().getIntent().getIntExtra( KEY_INDEX, -1 );

        retrofit2.Call<GetArticlesResponse> call;
        call = NewsAPI.getApi().getArticles();
        //call mundet mu thirr sinkronsilli ose asinkronislli, po masi e thirrim prej thredit kryesor atehere eshte asinkroslli
        call.enqueue( new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(retrofit2.Call<GetArticlesResponse> call, Response<GetArticlesResponse> response) {
                GetArticlesResponse getArticlesResponse = response.body();
                NewsStore.setNewsArticles( getArticlesResponse.getArticles() );

                Article article=NewsStore.getNewsArticles().get( 1 );
                SaveIntoDatabase task=new SaveIntoDatabase();
                task.execute(article );

                //Toast.makeText(BallinaFragment.this, "Response received", Toast.LENGTH_SHORT).show();
                homeNewsAdapter = new HomeNewsAdapter( getArticlesResponse.getArticles() );
                newsRecyclerView.setAdapter( homeNewsAdapter );

            }

            @Override
            public void onFailure(retrofit2.Call<GetArticlesResponse> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "Error received", Toast.LENGTH_SHORT).show();

            }
        } );

    }

    public void getFeedFromDatabase(){
        mDatabaza.merriLajmet( this );
    }


    public static void launch1(Context context, int index) {
        Intent intent = new Intent( context, LajmiDetajuar1.class );
        intent.putExtra( KEY_INDEX, index );
        context.startActivity( intent );

    }
//    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
//        inflater.inflate(R.menu.menu_main, menu);
//        MenuItem item = menu.findItem(R.id.search);
//        SearchView sv = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
//        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
//        MenuItemCompat.setActionView(item, sv);
//        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                System.out.println("search query submit");
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                System.out.println("tap");
//                return false;
//            }
//        });
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        return false;
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected( item );
//    }
//
//    private void search(SearchView searchView) {
//        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                homeNewsAdapter.getFilter().filter( newText );
//                return true;
//            }
//        } );
//
//
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate( R.menu.menu_main,menu );
        MenuItem menuItem=menu.findItem( R.id.search );
        SearchView searchView=(SearchView) MenuItemCompat.getActionView( menuItem );
        searchView.setOnQueryTextListener( this );
        super.onCreateOptionsMenu( menu, inflater );
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        homeNewsAdapter.getFilter().filter(newText);
        return true;
    }

    public boolean getNetworkAvailability(){
        return Utilis.isNetworkAvailable( getActivity().getApplicationContext() );
    }

    @Override
    public void onDeliverAllNews(List<Article> articles) {

    }

    @Override
    public void OnDeliverArticle(Article article) {
        homeNewsAdapter.getFilter();

    }

    @Override
    public void onHideDialog() {

    }

    public class SaveIntoDatabase extends AsyncTask<Article,Void,Void>{


        private final String TAG = SaveIntoDatabase.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Article... params) {

            Article article=params[0];
           String title= NewsStore.getNewsArticles().get( 1 ).getTitle();
            article=NewsStore.getNewsArticles().get( 1 );
            try {
                Log.d(TAG, "Values Got " +article.getTitle());
                mDatabaza.shtoLajme(article);

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }

            return null;
        }
    }
}
