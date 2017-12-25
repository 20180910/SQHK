package com.sk.sqhk.module.my.activity;

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

public class MyAccountActivity extends BaseActivity {
    @BindView(R.id.rv_my_shouyi)
    MyRecyclerView rv_my_shouyi;

    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的账户");
        return R.layout.act_my_account;
    }

    @Override
    protected void initView() {

        adapter=new LoadMoreAdapter(mContext,R.layout.item_my_shouyi,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, Object bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_my_shouyi.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
