package com.news.edoniskaraqica.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.news.edoniskaraqica.news.model.ArticleDetailed;
import com.news.edoniskaraqica.news.utils.DateUtils;

import java.util.List;

/**
 * Created by Edonis Karaçica on 3/13/2017.
 */

public class HomeNewsAdapterDetaje extends RecyclerView.Adapter<HomeNewsAdapterDetaje.HomeNewsViewHolder> {
    private List<ArticleDetailed> newsArticles1;

    public HomeNewsAdapterDetaje(List<ArticleDetailed> newsArticles1) {
        this.newsArticles1 = newsArticles1;
    }

    //arsyeja pse na nevoitet homenewsholder osht qe sa here qe ta bojme scroll, mos me th

    @Override
    public HomeNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_news,parent,false);
        HomeNewsViewHolder homeNewsViewHolder=new HomeNewsViewHolder(view);
        return homeNewsViewHolder;
    }
    int id;
    @Override
    public void onBindViewHolder(HomeNewsViewHolder holder, final int position) {
        ArticleDetailed newsArticle1=newsArticles1.get(position);
        //int n=-1;
        int n=-1;
        n= Integer.parseInt(newsArticle1.getId());
        //final int n= Integer.parseInt(newsArticle1.getId());
        Glide.with(holder.cardImageView.getContext()).load(newsArticle1.getUrlToImage()).centerCrop().into(holder.cardImageView);
        holder.cardTitleTextView.setText(newsArticle1.getTitle());
        holder.cardTimeTextView.setText(DateUtils.formatNewsApiDate(newsArticle1.getPublishedAt()));
        holder.cardContentTextView.setText(newsArticle1.getDescription());
        holder.ora.setText(newsArticle1.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LajmiDetajuar1.launch1(view.getContext(),position);
            }
        });




    }

    @Override
    public int getItemCount() {
        return newsArticles1.size();
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
