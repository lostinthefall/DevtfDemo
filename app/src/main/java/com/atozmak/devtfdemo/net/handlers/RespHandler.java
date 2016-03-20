package com.atozmak.devtfdemo.net.handlers;

/**
 * Created by Mak on 2016/3/19.
 */
public interface RespHandler<T, D> {
    public T parse(D data);
}
