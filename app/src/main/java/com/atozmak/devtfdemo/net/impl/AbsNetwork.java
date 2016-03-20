package com.atozmak.devtfdemo.net.impl;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.atozmak.devtfdemo.net.handlers.RespHandler;
import com.atozmak.devtfdemo.net.mgr.RequestQueueMgr;

import java.net.HttpRetryException;

/**
 * Created by Mak on 2016/3/19.
 */
public abstract class AbsNetwork<T, D> {
    static RequestQueue queue = RequestQueueMgr.getmRequestQueue();

    RespHandler<T, D> mRespHandler;

    protected void performRequest(Request<?> request) {
        queue.add(request);
    }

}
