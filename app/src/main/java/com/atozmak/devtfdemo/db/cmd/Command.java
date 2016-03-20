package com.atozmak.devtfdemo.db.cmd;

import android.database.sqlite.SQLiteDatabase;

import com.atozmak.devtfdemo.db.helper.DatabaseMgr;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.entities.Job;
import com.atozmak.devtfdemo.entities.Recommend;
import com.atozmak.devtfdemo.listeners.DataListener;

import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public abstract class Command<T> {

    public DataListener<T> dataListener;

    public Command() {
    }

    public Command(DataListener<T> dataListener) {
        this.dataListener = dataListener;
    }

    public final T execute() {
        SQLiteDatabase database = DatabaseMgr.getDatabase();
        DatabaseMgr.beginTransaction();
        T result = doInBackground(database);
        DatabaseMgr.setTransactionSuccess();
        DatabaseMgr.endTransaction();
        database.releaseReference();
        return result;
    }

    protected abstract T doInBackground(SQLiteDatabase database);

    public static abstract class NoReturnCmd extends Command<Void> {
    }

    public static abstract class ArticlesCommand extends Command<List<Article>> {
        public ArticlesCommand(DataListener<List<Article>> listener) {
            dataListener = listener;
        }
    }

    public static abstract class JobsCommand extends Command<List<Job>> {
        public JobsCommand(DataListener<List<Job>> listener) {
            dataListener = listener;
        }
    }

    public static abstract class RecommendCmd extends Command<List<Recommend>> {
        public RecommendCmd(DataListener<List<Recommend>> listener) {
            dataListener = listener;
        }
    }


}
