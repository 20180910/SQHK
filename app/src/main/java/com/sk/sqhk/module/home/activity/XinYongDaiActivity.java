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

public class XinYongDaiActivity extends BaseActivity {
    @BindView(R.id.rv_xinyongdai)
    MyRecyclerView rv_xinyongdai;

    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("信用贷");
        return R.layout.act_xinyongdai;
    }

    @Override
    protected void initView() {
        adapter=new LoadMoreAdapter(mContext,R.layout.item_hot_dai,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, Object bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_xinyongdai.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
