package com.atozmak.devtfdemo.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.ProviderTestCase2;

import com.atozmak.devtfdemo.db.cmd.Command;
import com.atozmak.devtfdemo.db.engine.DbExecutor;
import com.atozmak.devtfdemo.listeners.DataListener;

import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public abstract class AbsDBapi<T> {

    protected static DbExecutor sDbExecutor = DbExecutor.getExecutor();

    protected String mTableName;

    public AbsDBapi(String table) {
        mTableName = table;
    }

    public void saveItem(final T item) {
        sDbExecutor.execute(new Command.NoReturnCmd() {
            @Override
            protected Void doInBackground(SQLiteDatabase database) {
                database.insertWithOnConflict(
                        mTableName,
                        null,
                        toContentValues(item),
                        SQLiteDatabase.CONFLICT_REPLACE);
                return null;
            }
        });
    }

    protected ContentValues toContentValues(T item) {
        return null;
    }

    public void saveItems(List<T> datas) {
        for (T item : datas) {
            saveItem(item);
        }
    }

    public void loadDatasFromDB(DataListener<List<T>> listener) {
        sDbExecutor.execute(new Command<List<T>>(listener) {
            @Override
            protected List doInBackground(SQLiteDatabase database) {
                Cursor cursor = database.query(
                        mTableName, null, null, null,
                        null, null, loadDataOrderBy());
                List<T> result = parseResult(cursor);
                cursor.close();
                return result;
            }
        });
    }

    protected String loadDataOrderBy() {
        return "";
    }

    protected List<T> parseResult(Cursor cursor) {
        return null;
    }

    public void deleteWithWhereArgs(final String whereArgs) {
        sDbExecutor.execute(new Command<Void>() {
            @Override
            protected Void doInBackground(SQLiteDatabase database) {
                database.execSQL("delete from " + mTableName + whereArgs);
                return null;
            }
        });
    }

    public void deleteAll() {
        sDbExecutor.execute(new Command<Void>() {
            @Override
            protected Void doInBackground(SQLiteDatabase database) {
                database.execSQL("delete from " + mTableName);
                return null;
            }
        });
    }


}
