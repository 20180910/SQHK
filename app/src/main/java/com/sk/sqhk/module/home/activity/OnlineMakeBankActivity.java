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

public class OnlineMakeBankActivity extends BaseActivity {
    @BindView(R.id.rv_hot_bank)
    MyRecyclerView rv_hot_bank;
    @BindView(R.id.rv_hot_xinyongka)
    MyRecyclerView rv_hot_xinyongka;

    LoadMoreAdapter bankAdapter,xinyongKaAdapter;

    @Override
    protected int getContentView() {
        setAppTitle("在线办卡");
        return R.layout.act_online_make_bank;
    }

    @Override
    protected void initView() {
        bankAdapter=new LoadMoreAdapter(mContext,R.layout.item_hot_bank,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, Object bean) {

            }
        };
        rv_hot_bank.setAdapter(bankAdapter);

        xinyongKaAdapter=new LoadMoreAdapter(mContext,R.layout.item_hot_xinyongka,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, Object bean) {

            }
        };
        xinyongKaAdapter.setOnLoadMoreListener(this);
        rv_hot_xinyongka.setAdapter(xinyongKaAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
