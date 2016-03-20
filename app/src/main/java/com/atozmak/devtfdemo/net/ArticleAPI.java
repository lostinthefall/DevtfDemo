package com.atozmak.devtfdemo.net;

import com.android.volley.Response;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.listeners.DataListener;

import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public interface ArticleAPI {

    public void fetchArticles(
            int category,
            DataListener<List<Article>> listener,
            Response.ErrorListener errorListener
    );

    public void fetchArticleContent(
            String post_id,
            DataListener<String> listener,
            Response.ErrorListener errorListener
    );

    public void loadMore(
            int category,
            DataListener<List<Article>> listener,
            Response.ErrorListener errorListener
    );

}
