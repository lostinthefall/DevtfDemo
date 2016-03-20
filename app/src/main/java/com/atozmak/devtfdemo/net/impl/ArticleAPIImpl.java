package com.atozmak.devtfdemo.net.impl;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.listeners.DataListener;
import com.atozmak.devtfdemo.net.ArticleAPI;
import com.atozmak.devtfdemo.net.handlers.ArticlesHandler;
import com.atozmak.devtfdemo.net.mgr.RequestQueueMgr;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public class ArticleAPIImpl
        extends AbsNetwork<List<Article>, JSONArray>
        implements ArticleAPI {

    private int mPage = 1;

    public ArticleAPIImpl() {
        mRespHandler = new ArticlesHandler();
    }

    @Override
    public void fetchArticles(int category, DataListener<List<Article>> listener, Response.ErrorListener errorListener) {
        performRequest(1, category, listener, errorListener);
    }

    private void performRequest(int i, int category, final DataListener<List<Article>> listener, Response.ErrorListener errorListener) {
        JsonArrayRequest request = new JsonArrayRequest(
                "http://www.devtf.cn/api/v1/?type=articles&page=" + mPage
                        + "&count=20&category=" + category,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listener.onComplete(mRespHandler.parse(response));
                    }
                }, errorListener
        );
        RequestQueueMgr.getmRequestQueue().add(request);
    }

    @Override
    public void fetchArticleContent(String post_id, final DataListener<String> listener, Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(
                "http://www.devtf.cn/api/v1/?type=article&post_id=" + post_id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onComplete(response);
                    }
                }, errorListener
        );
        performRequest(request);
    }

    @Override
    public void loadMore(int category, DataListener<List<Article>> listener, Response.ErrorListener errorListener) {
        performRequest(++mPage, category, listener, errorListener);
    }
}
