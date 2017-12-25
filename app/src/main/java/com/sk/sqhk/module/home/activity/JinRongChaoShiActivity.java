package com.sk.sqhk.module.home.activity;

import android.view.View;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/21.
 */

public class JinRongChaoShiActivity extends BaseActivity {
    @BindView(R.id.rv_hot_dai)
    MyRecyclerView rv_hot_dai;

    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("金融超市");
        return R.layout.act_jinrong_chaoshi;
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter(mContext,R.layout.item_hot_dai,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, Object bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_hot_dai.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
