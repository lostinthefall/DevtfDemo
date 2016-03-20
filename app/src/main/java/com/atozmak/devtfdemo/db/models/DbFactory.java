package com.atozmak.devtfdemo.db.models;

import com.atozmak.devtfdemo.db.AbsDBapi;
import com.atozmak.devtfdemo.db.ArticleDetailDBapi;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.entities.Job;
import com.atozmak.devtfdemo.entities.Recommend;

/**
 * Created by Mak on 2016/3/19.
 */
public class DbFactory {

    public static AbsDBapi<Article> createArticleModel(){
        return new ArticleModel();
    }

    public static ArticleDetailDBapi createArticleDetailModel(){
        return new ArticleDetailModel();
    }

    public static AbsDBapi<Job>createJobModel(){
        return new JobsModel();
    }

    public static AbsDBapi<Recommend> createRecommendModel(){
        return new RecommendModel();
    }

    public static FavoriteDBapi createFavoriteModel(){
        return new FavoriteModel();
    }

}
