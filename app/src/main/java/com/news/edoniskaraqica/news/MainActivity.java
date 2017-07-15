package com.news.edoniskaraqica.news;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.news.edoniskaraqica.news.Databaza.Databaza;
import com.news.edoniskaraqica.news.Fragmentet.BallinaFragment;
import com.news.edoniskaraqica.news.Fragmentet.EkonomiaFragment;
import com.news.edoniskaraqica.news.Fragmentet.LajmeFragment;
import com.news.edoniskaraqica.news.Fragmentet.ShendetesiaFragment;
import com.news.edoniskaraqica.news.Fragmentet.SportFragment;
import com.news.edoniskaraqica.news.model.Article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    //private RecyclerView newsRecyclerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    HomeNewsAdapter homeNewsAdapter;
    ArrayList<Article> arrayList=new ArrayList<>(  );
    private static final String KEY_INDEX = "news_index";
    private RecyclerView newsRecyclerView;
    private Databaza mDatabaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        SimpleDateFormat smpData = new SimpleDateFormat( "hh:mm" );
        Article article;

//        mDatabaza=new Databaza(this);
//        article=NewsStore.getNewsArticles().get( 1 );
//        mDatabaza.shtoLajme( article );


        mDrawerLayout = (DrawerLayout) findViewById( R.id.drawerLayout );
        mToggle = new ActionBarDrawerToggle( this, mDrawerLayout, R.string.open, R.string.close );

        mDrawerLayout.addDrawerListener( mToggle );
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled( true );


//        newsRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerview);
//        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//
//        retrofit2.Call<GetArticlesResponse> call;
//        call = NewsAPI.getApi().getArticles();
//        //call mundet mu thirr sinkronsilli ose asinkronislli, po masi e thirrim prej thredit kryesor atehere eshte asinkroslli
//        call.enqueue(new Callback<GetArticlesResponse>() {
//            @Override
//            public void onResponse(retrofit2.Call<GetArticlesResponse> call, Response<GetArticlesResponse> response) {
//                GetArticlesResponse getArticlesResponse = response.body();
//                NewsStore.setNewsArticles(getArticlesResponse.getArticles());
//                Toast.makeText(MainActivity.this, "Response received", Toast.LENGTH_SHORT).show();
//                homeNewsAdapter = new HomeNewsAdapter(getArticlesResponse.getArticles());
//                newsRecyclerView.setAdapter(homeNewsAdapter);
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<GetArticlesResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Error received", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//    }


        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add( R.id.main_container,new BallinaFragment() );
        fragmentTransaction.commit();
        getSupportActionBar().setTitle( "Ballina" );

        navigationView=(NavigationView) findViewById( R.id.nav_menu );
        navigationView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_ballina:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace( R.id.main_container,new BallinaFragment() );
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle( "Ballina" );
                        item.setChecked( true );
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.nav_sport:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace( R.id.main_container,new SportFragment() );
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle( "Sport" );
                        item.setChecked( true );
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_ekonomi:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace( R.id.main_container,new EkonomiaFragment() );
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle( "Ekonomia" );
                        item.setChecked( true );
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_lajme:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace( R.id.main_container,new LajmeFragment() );
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle( "Lajme" );
                        item.setChecked( true );
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_shendetesi:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace( R.id.main_container,new ShendetesiaFragment() );
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle( "Shendetesia" );
                        item.setChecked( true );
                        mDrawerLayout.closeDrawers();
                        break;
                    }


                return true;
            }
        } );
    }


//    public static void launch1(Context context, int index) {
//        Intent intent = new Intent( context, LajmiDetajuar1.class );
//        intent.putExtra( KEY_INDEX, index );
//        context.startActivity( intent );
//    }




        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate( R.menu.menu_main,menu );
//        MenuItem menuItem=menu.findItem( R.id.search );
//        SearchView searchView=(SearchView) MenuItemCompat.getActionView( menuItem );
//        searchView.setOnQueryTextListener( this );
//        return true;
//    }
//
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//
//                homeNewsAdapter.getFilter().filter(newText);
//                return true;
//            }


    //
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate( R.menu.menu_main,menu );
//        MenuItem menuItem=menu.findItem( R.id.search);
//        return true;
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        MenuItem search = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
//        search(searchView);
//        return  true;
//    }
//    private void search(SearchView searchView) {
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                homeNewsAdapter.getFilter().filter(newText);
//                return true;
//            }
//        });
//    }
}

