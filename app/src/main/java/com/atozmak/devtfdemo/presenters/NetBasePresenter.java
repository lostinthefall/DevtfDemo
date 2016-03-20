package com.atozmak.devtfdemo.presenters;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.atozmak.devtfdemo.ui.interfaces.RefreshInterface;

/**
 * Created by Mak on 2016/3/19.
 */
public abstract class NetBasePresenter<T extends RefreshInterface<?>> {

    protected T mView;

    Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mView.onError(error);
        }
    };

}
