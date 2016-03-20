package com.atozmak.devtfdemo.ui.frgms;

import android.content.Intent;

import com.android.volley.VolleyError;
import com.atozmak.devtfdemo.adapters.ArticleAdapter;
import com.atozmak.devtfdemo.adapters.ArticleWithHeaderAdapter;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.listeners.OnItemClickListener;
import com.atozmak.devtfdemo.presenters.ArticlePresenter;
import com.atozmak.devtfdemo.ui.DetailActivity;
import com.atozmak.devtfdemo.ui.interfaces.ArticleViewInterface;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Mak on 2016/3/18.
 */
public class ArticlesFrgm extends RecyclerViewFrgm<Article> implements ArticleViewInterface {

    protected ArticleAdapter mAdapter;
    private ArticlePresenter mPresenter;
    protected int mCategory = Article.ALL;


    @Override
    protected void initPresenter() {
        mPresenter = new ArticlePresenter(this);
        mPresenter.loadArticlesFromDB();
    }

    @Override
    protected void initAdapter() {
        mAdapter = new ArticleWithHeaderAdapter(mDataSet);
        mAdapter.setOnItemClickListener(new OnItemClickListener<Article>() {
            @Override
            public void onClick(Article item) {
                if (item != null) {
                    loadArticle(item);
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void loadArticle(Article article) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("post_id", article.post_id);
        intent.putExtra("title", article.title);
        startActivity(intent);
    }

    @Override
    public void fetchDatas() {
        filterArticleByCategory();
        mPresenter.fetchArticles(mCategory);
    }

    private void filterArticleByCategory() {
        if ((mCategory == Article.ANDROID || mCategory == Article.IOS) && mDataSet.size() > 0) {
            Iterator<Article> it = mDataSet.iterator();
            while (it.hasNext()) {
                Article article = it.next();
                if (article.category != mCategory) {
                    it.remove();
                }
            }
        }
    }

    @Override
    public void onLoad() {
        mPresenter.loadModeArticles(mCategory);
    }

    @Override
    public void onRefresh() {
        mPresenter.fetchArticles(mCategory);
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError(VolleyError error) {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void fetchedData(List<Article> result) {
        mDataSet.clear();
        mDataSet.addAll(result);
        filterArticleByCategory();
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setLoading(false);
    }

    public void setArticleCategory(int category) {
        mCategory = category;
    }


}
