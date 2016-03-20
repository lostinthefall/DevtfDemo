package com.atozmak.devtfdemo.net.handlers;

import com.atozmak.devtfdemo.entities.Recommend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mak on 2016/3/20.
 */
public class RecommendHandler
        implements RespHandler<List<Recommend>, JSONArray> {
    @Override
    public List<Recommend> parse(JSONArray data) {
        List<Recommend> recommendList = new ArrayList<>();
        int length = data.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = data.optJSONObject(i);
            String title = jsonObject.optString("title");
            String imgUrl = jsonObject.optString("img_url");
            String url = jsonObject.optString("url");
            recommendList.add(new Recommend(title, url, imgUrl));
        }
        return recommendList;
    }
}
