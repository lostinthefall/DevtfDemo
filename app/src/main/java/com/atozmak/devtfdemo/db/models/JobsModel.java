package com.atozmak.devtfdemo.db.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.atozmak.devtfdemo.db.AbsDBapi;
import com.atozmak.devtfdemo.db.helper.DatabaseHelper;
import com.atozmak.devtfdemo.entities.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public class JobsModel extends AbsDBapi<Job> {
    public JobsModel() {
        super(DatabaseHelper.TABLE_JOBS);
    }

    @Override
    protected List<Job> parseResult(Cursor cursor) {
        List<Job> jobs = new ArrayList<>();
        while (cursor.moveToNext()) {
            Job item = new Job();
            item.company = cursor.getString(0);
            item.job = cursor.getString(1);
            item.desc = cursor.getString(2);
            item.email = cursor.getString(3);
            item.url = cursor.getString(4);
            jobs.add(item);
        }
        return jobs;
    }

    @Override
    protected ContentValues toContentValues(Job item) {
        ContentValues newValues = new ContentValues();
        newValues.put("company", item.company);
        newValues.put("job", item.job);
        newValues.put("job_desc", item.desc);
        newValues.put("email", item.email);
        newValues.put("url", item.url);
        return newValues;
    }
}
