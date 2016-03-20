package com.atozmak.devtfdemo.entities;

/**
 * Created by Mak on 2016/3/19.
 */
public class ArticleDetail {
    public String postId;
    public String content;

    public ArticleDetail() {
    }

    public ArticleDetail(String postId, String content) {
        this.postId = postId;
        this.content = content;
    }
}
