package com.atozmak.devtfdemo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atozmak.devtfdemo.R;
import com.atozmak.devtfdemo.entities.Article;

import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public class ArticleAdapter extends MyBaseAdapter<Article, RecyclerView.ViewHolder> {
    public ArticleAdapter(List<Article> mDataSet) {
        super(mDataSet);
    }

    @Override
    protected void bindDataToItemView(RecyclerView.ViewHolder holder, Article item) {
        if (holder instanceof ArticleViewHolder) {
            bindArticleToItemView((ArticleViewHolder) holder, item);
        }
    }

    protected void bindArticleToItemView(ArticleViewHolder holder, Article item) {
        holder.tvTitle.setText(item.title);
        holder.tvPublishTime.setText(item.publishTime);
        holder.tvAuthor.setText(item.author);
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvPublishTime;
        public TextView tvAuthor;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvArticleTitle);
            tvPublishTime = (TextView) itemView.findViewById(R.id.tvArticleTime);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvArticleAuthor);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createArticleViewHolder(parent);
    }

    protected RecyclerView.ViewHolder createArticleViewHolder(ViewGroup parent) {
        return new ArticleViewHolder(inflateItemView(parent, R.layout.item_article));
    }

}
