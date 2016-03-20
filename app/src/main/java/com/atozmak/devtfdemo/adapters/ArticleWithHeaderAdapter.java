package com.atozmak.devtfdemo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.atozmak.devtfdemo.R;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.presenters.RecommendPresenter;
import com.atozmak.devtfdemo.widgets.AutoScrollViewPager;
import com.atozmak.devtfdemo.widgets.CirclePageIndicator;

import java.util.List;

/**
 * Created by Mak on 2016/3/19.
 */
public class ArticleWithHeaderAdapter extends  ArticleAdapter {

    private static final int HEADER_TYPE=0;

    HeaderViewHolder mHeaderViewHolder;

    RecommendPresenter mPresenter;

    public ArticleWithHeaderAdapter(List<Article> mDataSet) {
        super(mDataSet);
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder{

        public AutoScrollViewPager autoScrollViewPager;
        public CirclePageIndicator mIndicator;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            autoScrollViewPager = (AutoScrollViewPager) itemView.findViewById(R.id.slide_viewpager);
            mIndicator = (CirclePageIndicator) itemView.findViewById(R.id.recommend_indicator);
        }
    }

}
