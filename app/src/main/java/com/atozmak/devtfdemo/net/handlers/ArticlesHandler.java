package com.atozmak.devtfdemo.net.handlers;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.atozmak.devtfdemo.entities.Article;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public class ArticlesHandler
        implements RespHandler<List<Article>, JSONArray> {

    @SuppressLint("SimpleDateFormat")
    @Override
    public List<Article> parse(JSONArray jsonArray) {
        List<Article> articleList = new LinkedList<>();
        int count = jsonArray.length();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 0; i < count; i++) {
            JSONObject itemObject = jsonArray.optJSONObject(i);
            Article articleItem = new Article();
            articleItem.title = itemObject.optString("title");
            articleItem.author = itemObject.optString("author");
            articleItem.post_id = itemObject.optString("post_id");

            String category = itemObject.optString("category");
            articleItem.category = TextUtils.isEmpty(category) ? 0 : Integer.valueOf(category);
            articleItem.publishTime = formatData(dateFormat, itemObject.optString("date"));
            articleList.add(articleItem);
        }
        return articleList;
    }

    private String formatData(SimpleDateFormat dateFormat, String dateString) {
        try {
            Date date = dateFormat.parse(dateString);
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}
