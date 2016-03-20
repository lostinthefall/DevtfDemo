package com.atozmak.devtfdemo.presenters;

import com.atozmak.devtfdemo.adapters.ArticleWithHeaderAdapter;
import com.atozmak.devtfdemo.adapters.HeaderRecommendAdapter;
import com.atozmak.devtfdemo.db.AbsDBapi;
import com.atozmak.devtfdemo.db.models.DbFactory;
import com.atozmak.devtfdemo.entities.Recommend;
import com.atozmak.devtfdemo.listeners.OnItemClickListener;
import com.atozmak.devtfdemo.net.RecommendAPI;
import com.atozmak.devtfdemo.net.impl.RecommendAPIImpl;

/**
 * Created by Mak on 2016/3/20.
 */
public class RecommendPresenter {

    RecommendAPI mRecommendAPI = new RecommendAPIImpl();

    AbsDBapi<Recommend>mDatabaseAPI = DbFactory.createRecommendModel();

    OnItemClickListener<Recommend>mRecommendListener;

    private ArticleWithHeaderAdapter.HeaderViewHolder  mHeaderViewHolder;

    HeaderRecommendAdapter mRecommendAdapter;


}
