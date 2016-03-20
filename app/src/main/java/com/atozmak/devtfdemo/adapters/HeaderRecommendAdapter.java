package com.atozmak.devtfdemo.adapters;

import android.view.View;

//import com.atozmak.devtfdemo.util.RecyclingPagerAdapter;

/**
 * Created by Mak on 2016/3/20.
 */
public class HeaderRecommendAdapter extends RecyclingPagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
