package com.atozmak.devtfdemo.db.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.SimpleTimeZone;

/**
 * Created by Mak on 2016/3/19.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "tech_frontier.db";
    static final int DB_VERSION = 1;

    public static final String TABLE_ARTICLES = "articles";
    public static final String TABLE_ARTICLE_CONTENT = "article_content";
    public static final String TABLE_FAVORITES = "favorites";
    public static final String TABLE_JOBS = "jobs";
    public static final String TABLE_RECOMMENDS = "recommends";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ARTICLES_TABLE_SQL);
        db.execSQL(CREATE_ARTICLE_CONTENT_TABLE_SQL);
        db.execSQL(CREATE_FAVORITES_TABLE_SQL);
        db.execSQL(CREATE_JOBS_TABLE_SQL);
        db.execSQL(CREATE_RECOMMANDS_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_ARTICLES);
        db.execSQL("DROP TABLE " + TABLE_ARTICLE_CONTENT);
        db.execSQL("DROP TABLE " + TABLE_FAVORITES);
        db.execSQL("DROP TABLE " + TABLE_JOBS);
        db.execSQL("DROP TABLE " + TABLE_RECOMMENDS);
        onCreate(db);
    }


    private static final String CREATE_ARTICLES_TABLE_SQL = "CREATE TABLE articles ( " +
            " post_id INTEGER PRIMARY KEY UNIQUE, " +
            " author VARCHAR(30) NOT NULL ," +
            " title VARCHAR(50) NOT NULL ," +
            " category INTEGER , " +
            " publish_time VARCHAR(50) " +
            " )";

    private static final String CREATE_ARTICLE_CONTENT_TABLE_SQL = "CREATE TABLE article_content ( " +
            " post_id INTEGER PRIMARY KEY UNIQUE, " +
            " content TEXT NOT NULL " +
            " )";

    private static final String CREATE_FAVORITES_TABLE_SQL = "CREATE TABLE favorites ( " +
            " aid INTEGER PRIMARY KEY UNIQUE, " +
            " uid INTEGER , UNIQUE(aid,uid)" +
            " )";

    private static final String CREATE_JOBS_TABLE_SQL = "CREATE TABLE jobs ( " +
            " company varchar(30) NOT NULL, " +
            " job varchar(30) NOT NULL, " +
            " job_desc varchar(50), " +
            " email varchar(30) NOT NULL, " +
            " url varchar(100) NOT NULL, " +
            " UNIQUE(company,job)" +
            " )";

    private static final String CREATE_RECOMMANDS_TABLE_SQL = "CREATE TABLE recommends ( " +
            " title varchar(50) NOT NULL UNIQUE, " +
            " url varchar(100) NOT NULL, " +
            " img_url varchar(200) " +
            " )";

}
