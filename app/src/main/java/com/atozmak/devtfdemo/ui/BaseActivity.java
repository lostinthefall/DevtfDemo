package com.atozmak.devtfdemo.ui;


import android.app.TaskStackBuilder;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.atozmak.devtfdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mak on 2016/3/18.
 */
public class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar mToolBar;

    protected FragmentManager mFrgmManager;
    protected int mFrgmContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mFrgmManager = getSupportFragmentManager();


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void setUpToolBar() {

        mToolBar.setTitle(R.string.app_name);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setFrgmContainer(int container) {
        mFrgmContainer = container;
    }

    protected void addFrgm(Fragment frgm) {
        mFrgmManager.beginTransaction().add(mFrgmContainer, frgm).commit();
    }

    protected void replaceFrgm(Fragment frgm) {
        mFrgmManager.beginTransaction().replace(mFrgmContainer, frgm).commit();
    }
}
