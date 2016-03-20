package com.atozmak.devtfdemo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atozmak.devtfdemo.adapters.MenuAdapter.MenuViewHolder;

import com.atozmak.devtfdemo.R;
import com.atozmak.devtfdemo.entities.MenuItem;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mak on 2016/3/18.
 */
public class MenuAdapter extends MyBaseAdapter<MenuItem, MenuViewHolder> {

    public MenuAdapter(List<MenuItem> dataSet) {
        super(dataSet);
    }

    @Override
    protected void bindDataToItemView(MenuViewHolder viewHolder, MenuItem item) {

        viewHolder.tvName.setText(item.text);
        viewHolder.ivUser.setImageResource(item.iconResId);
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuViewHolder(inflateItemView(parent, R.layout.menu_item));
    }


    static class MenuViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.userIconIv)
        public ImageView ivUser;
        @Bind(R.id.tvUserName)
        public TextView tvName;

        public MenuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
