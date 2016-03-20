package com.atozmak.devtfdemo.ui.frgms;

import com.android.volley.VolleyError;
import com.atozmak.devtfdemo.adapters.ArticleAdapter;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.presenters.ArticlePresenter;
import com.atozmak.devtfdemo.ui.interfaces.ArticleViewInterface;

import java.util.List;

/**
 * Created by Mak on 2016/3/18.
 */
public class ArticlesFrgm
        extends RecyclerViewFrgm<Article>
        implements ArticleViewInterface {

    protected ArticleAdapter mAdapter;
    private ArticlePresenter mPresenter;
    protected int mCategory = Article.ALL;


    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initAdapter() {
           mAdapter = new
    }

    @Override
    public void fetchDatas() {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(VolleyError error) {

    }

    @Override
    public void fetchedData(List<Article> result) {

    }
}
