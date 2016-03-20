package com.atozmak.devtfdemo.ui.interfaces;

import com.android.volley.VolleyError;

/**
 * Created by Mak on 2016/3/19.
 */
public interface RefreshInterface<T> extends BaseViewInterface<T> {
    public void showLoading();
    public void hideLoading();
    public void onError(VolleyError error);
}
