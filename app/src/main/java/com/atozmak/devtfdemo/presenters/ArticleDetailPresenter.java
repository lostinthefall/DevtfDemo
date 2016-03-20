package com.atozmak.devtfdemo.presenters;

import android.content.Intent;
import android.text.TextUtils;

import com.atozmak.devtfdemo.db.ArticleDetailDBapi;
import com.atozmak.devtfdemo.db.models.DbFactory;
import com.atozmak.devtfdemo.entities.ArticleDetail;
import com.atozmak.devtfdemo.listeners.DataListener;
import com.atozmak.devtfdemo.net.ArticleAPI;
import com.atozmak.devtfdemo.net.impl.ArticleAPIImpl;
import com.atozmak.devtfdemo.ui.interfaces.ArticleDetailView;

/**
 * Created by Mak on 2016/3/20.
 */
public class ArticleDetailPresenter extends NetBasePresenter<ArticleDetailView> {
    // 从网络上获取文章的Api
    ArticleAPI mArticleApi = new ArticleAPIImpl();
    /**
     *
     */
    ArticleDetailDBapi mArticleDBAPI = DbFactory.createArticleDetailModel();
    /**
     *
     */
   // final FavoriteDBAPI mFavoriteDBAPI = DbFactory.createFavoriteModel();

    //AuthPresenter mAuthPresenter;

    public ArticleDetailPresenter(ArticleDetailView view) {
        mView = view;
    }

    /**
     * 获取某篇文章的内容,先从数据库获取,如果数据库没有缓存则从网络上获取
     *
    // * @param post_id
     */
    public void fetchArticleContent(final String postId) {
        mArticleDBAPI.fetchArticleContent(postId, new DataListener<ArticleDetail>() {

            @Override
            public void onComplete(ArticleDetail result) {
                // 数据库中没有则通过网络获取
                if (TextUtils.isEmpty(result.content)) {
                    fetchFromNetwork(postId);
                } else {
                    mView.fetchedData(result);
                }
            }
        });
    }

    private void fetchFromNetwork(final String postId) {
        mArticleApi.fetchArticleContent(postId, new DataListener<String>() {

            @Override
            public void onComplete(String result) {
                ArticleDetail articleDetail = new ArticleDetail(postId, result);
                mView.fetchedData(articleDetail);
                // 存储文章到数据库中
                mArticleDBAPI.saveItem(articleDetail);
            }
        }, mErrorListener);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    /*    if (mAuthPresenter != null) {
            mAuthPresenter.onActivityResult(requestCode, resultCode, data);
        }*/
    }}