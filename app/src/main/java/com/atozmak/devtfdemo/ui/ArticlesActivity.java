package com.atozmak.devtfdemo.ui;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.atozmak.devtfdemo.R;
import com.atozmak.devtfdemo.adapters.MenuAdapter;
import com.atozmak.devtfdemo.entities.Article;
import com.atozmak.devtfdemo.entities.MenuItem;
import com.atozmak.devtfdemo.net.mgr.RequestQueueMgr;
import com.atozmak.devtfdemo.ui.frgms.ArticlesFrgm;
import com.atozmak.devtfdemo.listeners.OnItemClickListener;
import com.atozmak.devtfdemo.widgets.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class ArticlesActivity extends BaseActivity //implements LogoutInterface
{

    @Bind(R.id.drawer)
    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mToggle;

    @Bind(R.id.userIconIv)
    private CircleImageView circleImageView;

    @Bind(R.id.tvUserName)
    private TextView tvUserName;

    @Bind(R.id.menuRecyclerView)
    RecyclerView mMenuRecyclerView;

    ArticlesFrgm mArticlesFrgm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        setFrgmContainer(R.id.articlesContainer);

        initViews();

    }

    private void initViews() {
        setUpToolBar();
        mToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolBar,
                R.string.drawer_open,
                R.string.drawer_close);
        mToggle.syncState();
        mDrawerLayout.setDrawerListener(mToggle);

        initMenuLayout();
    }

    private void initMenuLayout() {
        mMenuRecyclerView.setLayoutManager(
                new LinearLayoutManager(getApplicationContext()));
        setUpMenuRecyclerView();
    }

    private void setUpMenuRecyclerView() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(getString(R.string.all), R.drawable.home));
        menuItems.add(new MenuItem("Android", R.drawable.android_icon));
        MenuAdapter menuAdapter = new MenuAdapter(menuItems);
        menuAdapter.setOnItemClickListener(new OnItemClickListener<MenuItem>() {
            @Override
            public void onClick(MenuItem item) {
                clickMenuItem(item);
            }
        });
        mMenuRecyclerView.setAdapter(menuAdapter);
    }

    private void clickMenuItem(MenuItem item) {
        mDrawerLayout.closeDrawers();
        switch (item.iconResId) {
            case R.drawable.home:
                mArticlesFrgm.setArticleCategory(Article.ALL);
                mArticlesFrgm.fetchDatas();
                replaceFrgm(mArticlesFrgm);
                break;
            case R.drawable.android_icon:
                replaceFrgm(mArticlesFrgm);
                mArticlesFrgm.setArticleCategory(Article.ANDROID);
                mArticlesFrgm.fetchDatas();
                break;
            case R.drawable.ios_icon:
                replaceFrgm(mArticlesFrgm);
                mArticlesFrgm.setArticleCategory(Article.IOS);
                mArticlesFrgm.fetchDatas();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RequestQueueMgr.getmRequestQueue().stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUserProfile();
    }

    private void initUserProfile() {
        //空
    }


}
