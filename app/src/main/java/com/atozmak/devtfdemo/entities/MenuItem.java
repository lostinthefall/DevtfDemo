package com.atozmak.devtfdemo.entities;

/**
 * Created by Mak on 2016/3/18.
 */
public class MenuItem {

    public int iconResId;
    public String text;

    public MenuItem() {
    }

    public MenuItem(String text, int iconResId) {
        this.iconResId = iconResId;
        this.text = text;
    }
}
