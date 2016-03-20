package com.atozmak.devtfdemo.listeners;

/**
 * Created by Mak on 2016/3/19.
 */
public interface DataListener<T> {
    public void onComplete(T result);
}
