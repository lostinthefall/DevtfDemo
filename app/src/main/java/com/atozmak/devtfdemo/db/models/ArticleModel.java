package com.atozmak.devtfdemo.db.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.atozmak.devtfdemo.db.AbsDBapi;
import com.atozmak.devtfdemo.db.helper.DatabaseHelper;
import com.atozmak.devtfdemo.entities.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public class ArticleModel extends AbsDBapi<Article> {
    public ArticleModel() {
        super(DatabaseHelper.TABLE_ARTICLES);
    }

    @Override
    protected String loadDataOrderBy() {
        return " publish_time DESC";
    }

    @Override
    protected List<Article> parseResult(Cursor cursor) {
        List<Article> articles = new ArrayList<>();
        while (cursor.moveToNext()) {
            Article item = new Article();
            item.post_id = cursor.getString(0);
            item.author = cursor.getString(1);
            item.title = cursor.getString(2);
            item.category = cursor.getInt(3);
            item.publishTime = cursor.getString(4);
            articles.add(item);
        }
        return articles;
    }

    @Override
    protected ContentValues toContentValues(Article item) {
        ContentValues newValues = new ContentValues();
        newValues.put("post_id", item.post_id);
        newValues.put("author", item.author);
        newValues.put("title", item.title);
        newValues.put("category", item.category);
        newValues.put("publishTime", item.publishTime);
        return newValues;
    }
}
