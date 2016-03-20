package com.atozmak.devtfdemo.net;

import com.atozmak.devtfdemo.entities.Recommend;
import com.atozmak.devtfdemo.listeners.DataListener;

import java.util.List;

/**
 * Created by Mak on 2016/3/20.
 */
public interface RecommendAPI {

    public void fetchRecommends(DataListener<List<Recommend>> listener);

}
