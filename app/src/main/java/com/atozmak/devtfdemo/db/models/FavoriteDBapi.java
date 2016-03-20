package com.atozmak.devtfdemo.db.models;

import com.atozmak.devtfdemo.db.AbsDBapi;
import com.atozmak.devtfdemo.db.helper.DatabaseHelper;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.listeners.DataListener;

/**
 * Created by Mak on 2016/3/19.
 */
public abstract class FavoriteDBapi extends AbsDBapi<Article> {
    public FavoriteDBapi() {
        super(DatabaseHelper.TABLE_FAVORITES);
    }

    public abstract void unFavoriteArticle(String postId);

    public abstract void isFavorited(String postId, DataListener<Boolean> listener);


}
