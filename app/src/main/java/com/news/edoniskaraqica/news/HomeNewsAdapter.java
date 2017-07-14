package com.news.edoniskaraqica.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.news.edoniskaraqica.news.model.Article;
import com.news.edoniskaraqica.news.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

/**
 * Created by Edonis Karaçica on 3/13/2017.
 */

public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.HomeNewsViewHolder> implements Filterable {
    private ArrayList<Article> newsArticles;
    private ArrayList<Article> mFilteredList;

    public HomeNewsAdapter(ArrayList<Article> newsArticles) {
        this.newsArticles = newsArticles;
        this.mFilteredList=newsArticles;
    }

    //arsyeja pse na nevoitet homenewsholder osht qe sa here qe ta bojme scroll, mos me th

    @Override
    public HomeNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news,parent,false);
        HomeNewsViewHolder homeNewsViewHolder=new HomeNewsViewHolder(view);
        return homeNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(HomeNewsViewHolder holder, final int position) {
        //final Article newsArticle=newsArticles.get(position);
        Article newsArticle=mFilteredList.get( position );
        String n="";
        n=newsArticle.getId();
        final int value=Integer.parseInt(n.toString());
        Glide.with(holder.cardImageView.getContext()).load(newsArticle.getUrlToImage()).centerCrop().into(holder.cardImageView);
        holder.cardTitleTextView.setText(newsArticle.getTitle());
        holder.cardTimeTextView.setText(DateUtils.formatNewsApiDate(newsArticle.getPublishedAt()));
        holder.cardContentTextView.setText(newsArticle.getDescription());
        holder.ora.setText(newsArticle.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final int newsA=newsArticle.getId();
                LajmiDetajuar1.launch1(view.getContext(),value);
            }
        });




    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString=charSequence.toString();

                if (charString.isEmpty()){
                    mFilteredList=newsArticles;

                }
                else {
                    ArrayList<Article> filteredList=new ArrayList<>();
                    for (Article article:newsArticles){
                        if (article.getTitle().toLowerCase().contains( charString )) {
                            filteredList.add( article );
                        }
                    }
                    mFilteredList=filteredList;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList=(ArrayList<Article>)filterResults.values;
                notifyDataSetChanged();

            }
        };
    }


    public static class HomeNewsViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImageView;
        TextView cardTitleTextView;
        TextView cardTimeTextView;
        TextView cardContentTextView;
        TextView ora;

        public HomeNewsViewHolder(View itemView) {
            super(itemView);
            cardImageView=(ImageView)itemView.findViewById(R.id.card_news_image);
            cardTitleTextView=(TextView)itemView.findViewById(R.id.card_news_title);
            cardTimeTextView=(TextView)itemView.findViewById(R.id.card_news_time);
            cardContentTextView=(TextView)itemView.findViewById(R.id.card_news_content);
            ora=(TextView)itemView.findViewById(R.id.ora);
        }
    }

}
