package com.atozmak.devtfdemo.widgets;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import static com.atozmak.devtfdemo.util.LogUtils.makeLogTag;

/**
 * Created by Mak on 2016/3/18.
 */
public class AutoLoadRecyclerView extends RecyclerView {

    private static final String TAG = makeLogTag("AutoLoadRecyclerView");
    OnLoadListener mLoadListener;
    boolean isLoading = false;
    boolean isValidDelay = true;


    public AutoLoadRecyclerView(Context context) {
        super(context);
    }

    public AutoLoadRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLoadRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode()) {
            return;
        }
        init();
    }

    private void init() {
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                checkLoadMore(dx, dy);
            }
        });
    }

    public void setOnLoadListener(OnLoadListener listener) {
        mLoadListener = listener;
    }

    private void checkLoadMore(int dx, int dy) {

        if (isBottom(dx, dy)
                && !isLoading
                && isValidDelay
                && mLoadListener != null) {
            isValidDelay = false;
            mLoadListener.onLoad();
            Log.v(TAG, "加载更多");
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    isValidDelay = true;
                }
            }, 1000);
        }
    }

    private boolean isBottom(int dx, int dy) {
        LinearLayoutManager manager = (LinearLayoutManager) getLayoutManager();
        int lastVisibleItem = manager.findLastVisibleItemPosition();
        int totalItemCount = manager.getItemCount();
        return lastVisibleItem >= totalItemCount - 4 && dy > 0;
    }

    public void setLoading(boolean loading) {
        this.isLoading = loading;
    }

    public static interface OnLoadListener {
        public void onLoad();
    }


}
