package com.atozmak.devtfdemo.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Mak on 2016/3/19.
 */
public class DatabaseMgr {

    private static SQLiteDatabase sDatabase;
    private static AtomicInteger sDbRef = new AtomicInteger(0);
    static DatabaseHelper sHelper;

    public static void init(Context context) {
        if (sDatabase == null && context != null) {
            synchronized (DatabaseMgr.class) {
                sHelper = new DatabaseHelper(context.getApplicationContext());
                sDatabase = sHelper.getWritableDatabase();
            }
        }
    }

    public static void releaseDatabase() {
        if (sDbRef.decrementAndGet() == 0) {
            closeDatabase();
        }
    }

    private static void closeDatabase() {
        if (sDatabase != null && sDatabase.isOpen()) {
            sDatabase.close();
            sDatabase = null;
        }
    }

    public static SQLiteDatabase getDatabase() {
        if (!sDatabase.isOpen()) {
            sDatabase = sHelper.getWritableDatabase();
        }
        sDbRef.incrementAndGet();
        return sDatabase;
    }

    public static void beginTransaction() {
        if (sDatabase != null) {
            sDatabase.beginTransaction();
        }
    }

    public static void setTransactionSuccess() {
        if (sDatabase != null) {
            sDatabase.setTransactionSuccessful();
        }
    }

    public static void endTransaction() {
        if (sDatabase != null) {
            sDatabase.endTransaction();
        }
    }


}
