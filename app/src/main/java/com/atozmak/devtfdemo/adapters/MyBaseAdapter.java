package com.atozmak.devtfdemo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atozmak.devtfdemo.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mak on 2016/3/18.
 */
public abstract class MyBaseAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    List<T> mDataSet = new ArrayList<>();
    OnItemClickListener<T> mItemClickListener;

    public MyBaseAdapter(List<T> mDataSet) {
        this.mDataSet = mDataSet;
    }

    protected T getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    protected View inflateItemView(ViewGroup viewGroup, int layoutId) {
        return inflateItemView(viewGroup, layoutId, false);
    }

    private View inflateItemView(ViewGroup viewGroup, int layoutId, boolean attach) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, attach);
    }

    @Override
    public final void onBindViewHolder(VH holder, int position) {
        final T item = getItem(position);
        bindDataToItemView(holder, item);
    }

    protected abstract void bindDataToItemView(VH holder, T item);

    public void setOnItemClickListener(OnItemClickListener<T> mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    protected void setUpItemViewClickListener(VH viewHolder, final T item) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onClick(item);
                }
            }
        });
    }

}
