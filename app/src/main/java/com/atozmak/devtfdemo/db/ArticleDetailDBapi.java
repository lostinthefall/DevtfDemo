package com.atozmak.devtfdemo.db;

import com.atozmak.devtfdemo.db.helper.DatabaseHelper;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.entities.ArticleDetail;
import com.atozmak.devtfdemo.listeners.DataListener;

/**
 * Created by Mak on 2016/3/19.
 */
public abstract class ArticleDetailDBapi extends AbsDBapi<ArticleDetail> {
    public ArticleDetailDBapi() {
        super(DatabaseHelper.TABLE_ARTICLE_CONTENT);
    }

    public abstract void fetchArticleContent(String postId,DataListener<ArticleDetail> listener);
}
