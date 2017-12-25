package com.sk.sqhk.module.select.activity;

import android.view.View;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/25.
 */

public class WoDeZhangDanActivity extends BaseActivity {
    @BindView(R.id.rv_wode_zhangdan)
    MyRecyclerView rv_wode_zhangdan;

    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的账单");
        return R.layout.act_wode_zhangdan;
    }

    @Override
    protected void initView() {

        adapter=new LoadMoreAdapter(mContext,R.layout.item_wode_zhangdan,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, Object bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_wode_zhangdan.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
