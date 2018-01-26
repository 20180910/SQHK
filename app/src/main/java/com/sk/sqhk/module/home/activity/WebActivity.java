package com.sk.sqhk.module.home.activity;

import android.view.View;
import android.webkit.WebView;

import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/26.
 */

public class WebActivity extends BaseActivity {
    @BindView(R.id.wv_web)
    WebView wv_web;
    @Override
    protected int getContentView() {
        setAppTitle("神奇还卡");
        return R.layout.act_web;
    }

    @Override
    protected void initView() {
        String webUrl = getIntent().getStringExtra(Constant.IParam.webUrl);
        initSimpleWebViewForUrl(wv_web,webUrl);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
