package com.sk.sqhk.module.home.activity;

import android.view.View;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/20.
 */

public class ShouYiMingXiActivity extends BaseActivity {
    @BindView(R.id.rv_shouyi_mingxi)
    MyRecyclerView rv_shouyi_mingxi;

    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("收益明细");
        return R.layout.act_shouyi_mingxi;
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter(mContext,R.layout.item_shouyi_mingxi,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, Object bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_shouyi_mingxi.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
