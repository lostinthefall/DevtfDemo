package com.atozmak.devtfdemo.db.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.atozmak.devtfdemo.db.AbsDBapi;
import com.atozmak.devtfdemo.db.helper.DatabaseHelper;
import com.atozmak.devtfdemo.entities.Recommend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public class RecommendModel extends AbsDBapi<Recommend> {
    public RecommendModel() {
        super(DatabaseHelper.TABLE_RECOMMENDS);
    }

    @Override
    protected List<Recommend> parseResult(Cursor cursor) {
        List<Recommend> recommends = new ArrayList<>();
        while (cursor.moveToNext()) {
            String title = cursor.getString(0);
            String url = cursor.getString(1);
            String imgUrl = cursor.getString(2);
            recommends.add(new Recommend(title, url, imgUrl));
        }
        return recommends;
    }

    @Override
    protected ContentValues toContentValues(Recommend item) {
        ContentValues newValues = new ContentValues();
        newValues.put("title", item.title);
        newValues.put("url", item.url);
        newValues.put("img_url", item.imgUrl);
        return newValues;
    }
}
