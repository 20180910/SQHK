package com.sk.sqhk.module.home.activity;

import android.view.View;
import android.webkit.WebView;

import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/20.
 */

public class HuoDongGuiZeActivity extends BaseActivity {
    @BindView(R.id.wv_gui_ze)
    WebView wv_gui_ze;

    @Override
    protected int getContentView() {
        setAppTitle("活动规则");
        return R.layout.act_huo_dong_gui_ze;
    }

    @Override
    protected void initView() {
        String content = getIntent().getStringExtra(Constant.IParam.huoDongGuiZe);
        initWebViewForContent(wv_gui_ze,content);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
