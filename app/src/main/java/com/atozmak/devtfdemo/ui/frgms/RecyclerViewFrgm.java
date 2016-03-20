package com.atozmak.devtfdemo.ui.frgms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atozmak.devtfdemo.R;
import com.atozmak.devtfdemo.widgets.AutoLoadRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Mak on 2016/3/18.
 */
public abstract class RecyclerViewFrgm<T>
        extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener, AutoLoadRecyclerView.OnLoadListener {

    protected SwipeRefreshLayout mSwipeRefreshLayout;

    protected AutoLoadRecyclerView mRecyclerView;
    final protected List<T> mDataset = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frgm_recyclerview, container, false);
        initRefreshView(rootView);
        initAdapter();
        initPresenter();
        fetchDatas();
        mSwipeRefreshLayout.setRefreshing(true);

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    protected void initRefreshView(View rootView) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (AutoLoadRecyclerView) rootView.findViewById(R.id.articles_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setOnLoadListener(this);
    }

    protected abstract void initPresenter();

    protected abstract void initAdapter();

    public abstract void fetchDatas();


    ;

}
