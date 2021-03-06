package com.news.edoniskaraqica.news.Fragmentet;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.news.edoniskaraqica.news.HomeNewsAdapter;
import com.news.edoniskaraqica.news.LajmiDetajuar1;
import com.news.edoniskaraqica.news.NewsStore;
import com.news.edoniskaraqica.news.R;
import com.news.edoniskaraqica.news.model.GetArticlesResponse;
import com.news.edoniskaraqica.news.networking.NewsAPI;

import java.text.SimpleDateFormat;

import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EkonomiaFragment extends Fragment implements SearchView.OnQueryTextListener {
    private RecyclerView newsRecyclerView;
    private static final String KEY_INDEX="news_index";
    private HomeNewsAdapter homeNewsAdapter;
    


    public EkonomiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView1=inflater.inflate( R.layout.fragment_ekonomia,null );
        newsRecyclerView=(RecyclerView) rootView1.findViewById( R.id.activity_main_recyclerview_ekonomia);
        newsRecyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ));
        return rootView1;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lajmi_detajuar1);

        SimpleDateFormat smpData=new SimpleDateFormat("hh:mm");




        //newsRecyclerView=findViewById(R.id.activity_main_recyclerview_ballina);
        // newsRecyclerView=(RecyclerView) newsRecyclerView.findViewById(R.id.activity_main_recyclerview_ballina);
        //newsRecyclerView.setLayoutManager(new LinearLayoutManager());
        //newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //newsRecyclerView=getView().findViewById();


        //retrofit2.Call<Artikulli> call;
        //int index=getIntent().getIntExtra(KEY_INDEX,1);


        int index=getActivity().getIntent().getIntExtra(KEY_INDEX,-1);

        retrofit2.Call<GetArticlesResponse> call;
        call = NewsAPI.getApi().getArticles3();
        //call mundet mu thirr sinkronsilli ose asinkronislli, po masi e thirrim prej thredit kryesor atehere eshte asinkroslli
        call.enqueue(new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(retrofit2.Call<GetArticlesResponse> call, Response<GetArticlesResponse> response) {
                GetArticlesResponse getArticlesResponse = response.body();
                NewsStore.setNewsArticles(getArticlesResponse.getArticles());
                //Toast.makeText(BallinaFragment.this, "Response received", Toast.LENGTH_SHORT).show();
                HomeNewsAdapter homeNewsAdapter = new HomeNewsAdapter(getArticlesResponse.getArticles());
                newsRecyclerView.setAdapter(homeNewsAdapter);
            }

            @Override
            public void onFailure(retrofit2.Call<GetArticlesResponse> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "Error received", Toast.LENGTH_SHORT).show();

            }
        });



    }


    public static void launch1(Context context, int index){
        Intent intent=new Intent(context,LajmiDetajuar1.class);
        intent.putExtra(KEY_INDEX,index);
        context.startActivity(intent);

    }

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
}
