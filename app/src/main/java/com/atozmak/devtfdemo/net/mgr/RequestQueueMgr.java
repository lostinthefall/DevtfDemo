package com.atozmak.devtfdemo.net.mgr;

import android.content.ContentValues;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Mak on 2016/3/19.
 */
public class RequestQueueMgr {

    static RequestQueue mRequestQueue;

    private RequestQueueMgr() {
    }

    public static void init(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
    }

    public static RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }


}
