package com.atozmak.devtfdemo.db.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.atozmak.devtfdemo.db.cmd.Command;
import com.atozmak.devtfdemo.db.helper.DatabaseHelper;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.listeners.DataListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public class FavoriteModel extends FavoriteDBapi {

    @Override
    protected ContentValues toContentValues(Article article) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("aid", article.post_id);
        contentValues.put("uid", LoginSession.getLoginSession().getUserInfo().uid);
        return contentValues;
    }

    @Override
    public void loadDatasFromDB(DataListener<List<Article>> listener) {
        if (listener != null) {
            sDbExecutor.execute(new Command.ArticlesCommand(listener) {
                @Override
                protected List<Article> doInBackground(SQLiteDatabase database) {
                    List<String> articleList = findMyFavoriteArticles(database);
                    return loadArticles(database, articleList);
                }
            });
        }
    }

    private List<Article> loadArticles(SQLiteDatabase database,
                                       List<String> articleIds) {
        List<Article> result = new ArrayList<>();
        for (String post_id :
                articleIds) {
            Article item = queryArticleWithId(database, post_id);
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }

    private List<String> findMyFavoriteArticleIds(SQLiteDatabase database) {
        String[] columns = new String[]{"aid"};
        String[] selectionArgs = new String[]{LoginSession.getLoginSession().getUserInfo().uid};
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_FAVORITES, columns, "uid=?", selectionArgs,
                null, null, null
        );
        List<String> articleList = parseArticlePostIds(cursor);
        cursor.close();
        return articleList;
    }

    private List<String> parseArticlePostIds(Cursor cursor) {
        List<String> articleList = new ArrayList<>();
        while (cursor.moveToNext()) {
            articleList.add(cursor.getString(0));
        }
        return articleList;
    }

    private Article queryArticleWithId(SQLiteDatabase database, String postId) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_ARTICLES, null, "post_id=?",
                new String[]{postId}, null, null, null);
        if (cursor.moveToNext()) {
            return parseArticle(cursor);
        }
        return null;
    }

    private Article parseArticle(Cursor cursor) {
        Article article = new Article();
        article.post_id = cursor.getString(0);
        article.author = cursor.getString(1);
        article.title = cursor.getString(2);
        article.category = cursor.getInt(3);
        article.publishTime = cursor.getString(4);
        cursor.close();
        return article;
    }

    private List<String> findMyFavoriteArticles(SQLiteDatabase database) {
        String[] columns = new String[]{"aid"};
        String[] selectionArgs = new String[]{
                LoginSession.getLoginSession().getUserInfo().uid};
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_FAVORITES, columns, "uid=?",
                selectionArgs, null, null, null);
        List<String> articleList = parseArticlePostIds(cursor);
        cursor.close();
        return articleList;
    }

    @Override
    public void unFavoriteArticle(String postId) {
        deleteWithWhereArgs(" where aid=" + postId);
    }

    @Override
    public void isFavorited(final String postId, DataListener<Boolean> listener) {
        sDbExecutor.execute(new Command<Boolean>(listener) {
            @Override
            protected Boolean doInBackground(SQLiteDatabase database) {
                final String[] selectArgs = new String[]{
                        postId, LoginSession.getLoginSession().getUserInfo().uid
                };
                Cursor cursor = database.rawQuery(
                        "select * from " + DatabaseHelper.TABLE_FAVORITES +
                                " where aid = ? AND uid =?", selectArgs
                );
                int result = cursor.getCount();
                cursor.close();
                return result > 0;
            }
        });
    }
}
