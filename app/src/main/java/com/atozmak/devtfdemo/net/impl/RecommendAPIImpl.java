package com.atozmak.devtfdemo.net.impl;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.atozmak.devtfdemo.entities.Recommend;
import com.atozmak.devtfdemo.listeners.DataListener;
import com.atozmak.devtfdemo.net.RecommendAPI;
import com.atozmak.devtfdemo.net.handlers.RecommendHandler;
import com.atozmak.devtfdemo.net.handlers.RespHandler;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Mak on 2016/3/20.
 */
public class RecommendAPIImpl
        extends AbsNetwork<List<Recommend>, JSONArray>
        implements RecommendAPI {

    public RecommendAPIImpl() {
        mRespHandler = new RecommendHandler();
    }

    @Override
    public void fetchRecommends(final DataListener<List<Recommend>> listener) {
        JsonArrayRequest request = new JsonArrayRequest(
                "http://www.devtf.cn/api/v1/?type=recommends",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (listener != null) {
                            listener.onComplete(mRespHandler.parse(response));
                        }
                    }
                }, null
        );
        performRequest(request);
    }
}
