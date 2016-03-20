package com.atozmak.devtfdemo.presenters;

import android.annotation.SuppressLint;

import com.atozmak.devtfdemo.db.AbsDBapi;
import com.atozmak.devtfdemo.db.models.DbFactory;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.listeners.DataListener;
import com.atozmak.devtfdemo.net.ArticleAPI;
import com.atozmak.devtfdemo.net.impl.ArticleAPIImpl;
import com.atozmak.devtfdemo.ui.interfaces.ArticleViewInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public class ArticlePresenter extends NetBasePresenter<ArticleViewInterface> {

    //Model
    AbsDBapi<Article> mArticleModel = DbFactory.createArticleModel();

    //从网络上获取文章的api
    ArticleAPI mArticleApi = new ArticleAPIImpl();

    List<Article> mArticles = new ArrayList<>();

    private static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private boolean isNoMoreArticles = false;

    public ArticlePresenter(ArticleViewInterface viewInterface) {
        mView = viewInterface;
    }

    public void fetchArticles(int category) {
        mView.showLoading();
        mArticleApi.fetchArticles(category, new DataListener<List<Article>>() {
            @Override
            public void onComplete(List<Article> result) {
                fetchDataFinished(result);
            }
        }, mErrorListener);
    }

    public void loadModeArticles(int category) {
        if (isNoMoreArticles) {
            return;
        }
        mView.showLoading();
        mArticleApi.loadMore(category, new DataListener<List<Article>>() {
            @Override
            public void onComplete(List<Article> result) {
                fetchDataFinished(result);
                if (result.size() == 0) {
                    isNoMoreArticles = true;
                }
            }
        }, mErrorListener);


    }

    private void fetchDataFinished(List<Article> result) {
        mArticles.removeAll(result);
        mArticles.addAll(result);
        sortArticles(mArticles);
        mView.fetchedData(mArticles);
        mView.hideLoading();
        mArticleModel.saveItems(result);
    }

    private void sortArticles(List<Article> mArticles) {
        Collections.sort(mArticles, mArticleComparator);
    }

    @SuppressLint("SimpleDateFormat")
    Comparator<Article> mArticleComparator = new Comparator<Article>() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);

        @Override
        public int compare(Article lhs, Article rhs) {

            try {
                long lTime = df.parse(lhs.publishTime).getTime();
                long rTime = df.parse(rhs.publishTime).getTime();
                return (int) Math.abs(lTime - rTime);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        }
    };

    public void loadArticlesFromDB(){
        mArticleModel.loadDatasFromDB(new DataListener<List<Article>>() {
            @Override
            public void onComplete(List<Article> result) {
                mView.fetchedData(result);
            }
        });
    }

}
