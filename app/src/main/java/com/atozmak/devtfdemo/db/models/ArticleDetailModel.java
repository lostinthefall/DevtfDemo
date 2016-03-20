package com.atozmak.devtfdemo.db.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.atozmak.devtfdemo.db.ArticleDetailDBapi;
import com.atozmak.devtfdemo.db.cmd.Command;
import com.atozmak.devtfdemo.entities.ArticleDetail;
import com.atozmak.devtfdemo.listeners.DataListener;
import com.atozmak.devtfdemo.ui.DetailActivity;

/**
 * Created by Mak on 2016/3/19.
 */
public class ArticleDetailModel extends ArticleDetailDBapi {

    @Override
    protected ContentValues toContentValues(ArticleDetail detail) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("post_id", detail.postId);
        contentValues.put("content", detail.content);
        return contentValues;
    }

    @Override
    public void fetchArticleContent(final String postId,
                                    DataListener<ArticleDetail> listener) {
        sDbExecutor.execute(new Command<ArticleDetail>(listener) {
            @Override
            protected ArticleDetail doInBackground(SQLiteDatabase database) {
                Cursor cursor = database.query(mTableName,
                        new String[]{"content"},
                        "post_id=?", new String[]{postId},
                        null, null, null);
                String result = queryArticleContent(cursor);
                cursor.close();
                return new ArticleDetail(postId, result);
            }
        });
    }

    private String queryArticleContent(Cursor cursor) {
        return cursor.moveToNext() ? cursor.getString(0) : "";
    }
}
